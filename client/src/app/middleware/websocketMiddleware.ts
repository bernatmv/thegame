import * as Rx from 'rx';
import AppConfig from '../../config/appConfig';
import { debug } from '../../common/service/models/appLogger';
import Connection from '../../common/stream/connection/connection';
import WebsocketMessage from '../../common/stream/models/websocketMessage';
import ActionsConstants from '../../common/constants/actionsConstants';
import * as ChatActions from '../actions/chatActions';

const socketMiddleware = (function(){

    let chatStore = null;
    let me = 'User' + Math.ceil(Math.random() * 1000);

    let chatConnection: Connection = new Connection(AppConfig.endpoints.chat, () => {
        //Websocket open and ready to send/receive
        if (chatStore) {
            chatStore.dispatch(ChatActions.connectedToChat());
        }
    });

    let chatStream: Rx.Observable<WebsocketMessage> = chatConnection.getStream();
    chatStream.subscribe(
        (message: WebsocketMessage) => { //new message received
            if (chatStore && message.sender !== me) {
                chatStore.dispatch(ChatActions.receiveChat(message));
            }
        },
        (error) => {}, //error
        () => { //completed
            if (chatStore) {
                chatStore.dispatch(ChatActions.disconnectedFromChat());
            }
        }
    );

    return store => next => action => {
        chatStore = store;
        switch(action.type) {
            // Intercept when we want to send a message
            case ActionsConstants.SendChat:
                let message = {
                    sender: action.payload.sender ? action.payload.sender : me,
                    message: action.payload.message
                };
                chatConnection.sendMessage(message);
                action.payload = message;
                return next(action);
            default: // All those actions that we don't want to intercept, pass along to the reducer
                return next(action);
        }
    };
})();
export default socketMiddleware;