import * as Rx from 'rx';
import AppConfig from '../../config/appConfig';
import { debug } from '../../common/service/models/appLogger';
import SystemConstants from '../../common/constants/systemConstants';
import ActionsConstants from '../../common/constants/actionsConstants';
import * as ChatActions from '../actions/chatActions';
import * as SystemActions from '../actions/systemActions';
import Connection from '../../common/stream/connection/connection';
import MapServiceImpl from '../../common/service/mapServiceImpl';
import AuthServiceImpl from '../../common/service/authServiceImpl';
import WebsocketMessage from '../../common/stream/models/websocketMessage';
import ChatMessageDto from '../../common/service/dtos/chatMessageDto';
import RoomDto from '../../common/service/dtos/roomDto';

const _mapService = new MapServiceImpl();
const _authService = new AuthServiceImpl();

// PROCESSES MESSAGES FROM THE SERVER

const processReceivedChatMessage = (message: ChatMessageDto, store: any, userId: string): void => {
    if (message.sender !== userId) {
        store.dispatch(ChatActions.receiveChat(Object.assign({}, message, { received: Date.now() })));
    }
};

const processLoadRoomMessage = (room: RoomDto, store: any): void => {
    store.dispatch(SystemActions.loadRoom(_mapService.loadRoom(room)));
};

const processPlayerEntersRoomMessage = (userId: string, from: string, store: any): void => {
    store.dispatch(SystemActions.playerEntersRoom({
        user: _authService.getUser(userId),
        from: from
    }));
};

const processPlayerLeavesRoomMessage = (userId: string, exit: string, store: any): void => {
    store.dispatch(SystemActions.playerLeavesRoom({
        user: _authService.getUser(userId),
        exit: exit
    }));
};

export const processMessages = (stream: Rx.Observable<WebsocketMessage>, store: any, userId: string): void => {
    stream.subscribe(
        //new message received
        (message: any) => {
            debug(`New message from ${AppConfig.endpoints.websocket} through websocket`, message);
            if (store && message.kind === SystemConstants.ChatMessage) {
                processReceivedChatMessage(message, store, userId);
            }
            if (store && message.kind === ActionsConstants.LoadRoom) {
                processLoadRoomMessage(message, store);
            }
            if (store && message.kind === ActionsConstants.PlayerEntersRoom) {
                processPlayerEntersRoomMessage(message.player, message.from, store);
            }
            if (store && message.kind === ActionsConstants.PlayerLeavesRoom) {
                processPlayerLeavesRoomMessage(message.player, message.exit, store);
            }
        },
        //error
        (error) => {},
        //completed
        () => {
            if (store) {
                store.dispatch(ChatActions.disconnectedFromChat());
            }
        }
    );

};

// SENDS MESSAGES TO THE SERVE

export const sendChatMessage = (connection: Connection, action: any): WebsocketMessage => {
    let message = {
        kind: SystemConstants.ChatMessage,
        message: action.payload.message
    };
    connection.sendMessage(message);
    return message;
};

export const sendMoveMessage = (connection: Connection, action: any): void => {
    let message = {
        kind: ActionsConstants.Move,
        direction: action.payload
    };
    connection.sendMessage(message);
};