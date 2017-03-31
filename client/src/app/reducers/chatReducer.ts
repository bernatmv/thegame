import { handleActions } from 'redux-actions';
import RootState from './state/rootState';
import ChatMessage from './models/chatMessage';
import ConnectedModel from './models/connectedModel';
import SystemConstants from '../../common/constants/systemConstants';
import ActionsConstants from '../../common/constants/actionsConstants';
import ConnectionStatus from '../../common/stream/models/connectionStatus';

const initialState: RootState = {
    chats: [{
        sender: SystemConstants.SystemUser,
        message: 'Welcome to TheGame!',
        received: new Date()
    }],
    connectionStatus: ConnectionStatus.Connecting,
    userId: ''
};

export default handleActions<RootState, ChatMessage | ConnectedModel>({
    [ActionsConstants.ReceiveChat]: (state: RootState, action: ReduxActions.Action<ChatMessage>): RootState => {
        return Object.assign({}, state, { chats: addMessageToChat(state.chats, action) });
    },
    [ActionsConstants.SendChat]: (state: RootState, action: ReduxActions.Action<ChatMessage>): RootState => {
        return Object.assign({}, state, { chats: addMessageToChat(state.chats, action) });
    },
    [ActionsConstants.ConnectingToChat]: (state: RootState, action: ReduxActions.Action<void>): RootState => {
        return Object.assign({}, state, { connectionStatus: ConnectionStatus.Connecting});
    },
    [ActionsConstants.ConnectedToChat]: (state: RootState, action: ReduxActions.Action<ConnectedModel>): RootState => {
        return Object.assign({}, state, { userId: action.payload.userId, connectionStatus: ConnectionStatus.Connected});
    },
    [ActionsConstants.DisconnectedFromChat]: (state: RootState, action: ReduxActions.Action<void>): RootState => {
        return Object.assign({}, state, { connectionStatus: ConnectionStatus.Disconnected});
    }
}, initialState);

function addMessageToChat(chats: Array<ChatMessage>, action: ReduxActions.Action<ChatMessage>): Array<ChatMessage> {
    return [
        ...chats,
        action.payload
    ].slice(-50);
}