import * as Rx from 'rx';
import AppConfig from '../../config/appConfig';
import { debug } from '../../common/service/models/appLogger';
import {processMessages} from './apiMiddleware';
import Connection from '../../common/stream/connection/connection';
import WebsocketMessage from '../../common/stream/models/websocketMessage';
import SystemConstants from '../../common/constants/systemConstants';
import ActionsConstants from '../../common/constants/actionsConstants';
import * as ChatActions from '../actions/chatActions';

const socketMiddleware = (function(){
    // connection
    let _store = null;
    let connection: Connection = null;
    let stream: Rx.Observable<WebsocketMessage> = null;
    // user
    let me = null;

    let initialize = (userId: string) => {
        me = userId;
        connection = new Connection(AppConfig.endpoints.websocket + userId, () => {
            //Websocket open and ready to send/receive
            _store.dispatch(ChatActions.connectedToChat({userId: userId}));
        });
        stream = connection.getStream();
        processMessages(stream, _store, userId);
    };

    return store => {
        _store = store;
        return next => action => {
            switch(action.type) {
                // Intercept the login message so we can initialize the connection
                case ActionsConstants.Login:
                    initialize(action.payload);
                    return next(action);
                // Intercept when we want to send a message
                case ActionsConstants.SendChat:
                    let message = {
                        kind: SystemConstants.ChatMessage,
                        message: action.payload.message
                    };
                    connection.sendMessage(message);
                    action.payload = message;
                    return next(action);
                // All those actions that we don't want to intercept, pass along to the reducer
                default:
                    return next(action);
            }
        };
    };
})();
export default socketMiddleware;