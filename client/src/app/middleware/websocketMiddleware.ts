import * as Rx from 'rx';
import AppConfig from '../../config/appConfig';
import { debug } from '../../common/service/models/appLogger';
import Connection from '../../common/stream/connection/connection';
import WebsocketMessage from '../../common/stream/models/websocketMessage';
import SystemConstants from '../../common/constants/systemConstants';
import ActionsConstants from '../../common/constants/actionsConstants';
import MapServiceImpl from './services/mapServiceImpl';
import * as ChatActions from '../actions/chatActions';
import * as SystemActions from '../actions/systemActions';

const socketMiddleware = (function(){
    // connection
    let _store = null;
    let chatConnection: Connection = null;
    let chatStream: Rx.Observable<WebsocketMessage> = null;
    let me = 'User' + Math.ceil(Math.random() * 1000);
    // map
    let mapService = new MapServiceImpl();
    let currentRoom = mapService.getRoom('beta-room-001');

    let initialize = () => {
        _store.dispatch(SystemActions.loadRoom(currentRoom));

        chatConnection = new Connection(AppConfig.endpoints.chat, () => {
            //Websocket open and ready to send/receive
            _store.dispatch(ChatActions.connectedToChat({
                userId: me
            }));
        });

        chatStream = chatConnection.getStream();
        chatStream.subscribe(
            (message: WebsocketMessage) => { //new message received
                debug(`New message from ${AppConfig.endpoints.chat} through websocket`, message);
                if (_store && message.kind === SystemConstants.ChatMessage) {
                    if (message.sender !== me) {
                        _store.dispatch(ChatActions.receiveChat(message));
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
                    chatConnection.sendMessage(message);
                    action.payload = message;
                    return next(action);
                default: // All those actions that we don't want to intercept, pass along to the reducer
                    return next(action);
            }
        }
    };
})();
export default socketMiddleware;