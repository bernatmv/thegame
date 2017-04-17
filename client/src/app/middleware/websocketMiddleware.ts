import * as Rx from 'rx';
import AppConfig from '../../config/appConfig';
import { debug } from '../../common/service/models/appLogger';
import Connection from '../../common/stream/connection/connection';
import WebsocketMessage from '../../common/stream/models/websocketMessage';
import SystemConstants from '../../common/constants/systemConstants';
import ActionsConstants from '../../common/constants/actionsConstants';
import * as ChatActions from '../actions/chatActions';
import * as SystemActions from '../actions/systemActions';
import * as AuthActions from '../actions/authActions';

const socketMiddleware = (function(){
    // connection
    let _store = null;
    let connection: Connection = null;
    let stream: Rx.Observable<WebsocketMessage> = null;
    let me = 'User' + Math.ceil(Math.random() * 1000);
    // map
    let currentRoom = 'beta-room-001';

    let initialize = () => {
        _store.dispatch(SystemActions.loadRoom(currentRoom));

        connection = new Connection(AppConfig.endpoints.websocket, () => {
            //Websocket open and ready to send/receive
            _store.dispatch(ChatActions.connectedToChat({
                userId: me
            }));
        });

        stream = connection.getStream();
        stream.subscribe(
            (message: WebsocketMessage) => { //new message received
                debug(`New message from ${AppConfig.endpoints.websocket} through websocket`, message);
                if (_store && message.kind === SystemConstants.ChatMessage) {
                    if (message.sender !== me) {
                        _store.dispatch(ChatActions.receiveChat(Object.assign({}, message, { received: Date.now() })));
                    }
                }
            },
            (error) => {}, //error
            () => { //completed
                if (_store) {
                    _store.dispatch(ChatActions.disconnectedFromChat());
                }
            }
        );
    }

    return store => {
        _store = store;
        initialize();
        return next => action => {
            switch(action.type) {
                // Intercept when we want to send a message
                case ActionsConstants.SendChat:
                    let message = {
                        kind: SystemConstants.ChatMessage,
                        sender: action.payload.sender ? action.payload.sender : me,
                        message: action.payload.message,
                        received: ''
                    };
                    connection.sendMessage(message);
                    action.payload = message;
                    return next(action);
                default: // All those actions that we don't want to intercept, pass along to the reducer
                    return next(action);
            }
        }
    };
})();
export default socketMiddleware;