import { handleActions } from 'redux-actions';
import RootState from './state/rootState';
import ChatMessage from './models/chatMessage';
import SystemConstants from '../../common/constants/systemConstants';
import ActionsConstants from '../../common/constants/actionsConstants';

const initialState: RootState = {
    chats: [{
        sender: SystemConstants.SystemUser,
        message: 'Welcome to TheGame!',
        received: new Date()
    }]
};

export default handleActions<RootState, ChatMessage>({
    [ActionsConstants.ReceiveChat]: (state: RootState, action) => {
        return addMessageToChat(state, action);
    },
    [ActionsConstants.SendChat]: (state: RootState, action) => {
        return addMessageToChat(state, action);
    },
    [ActionsConstants.ConnectingToChat]: (state, action) => state,
    [ActionsConstants.ConnectedToChat]: (state, action) => state,
    [ActionsConstants.DisconnectedFromChat]: (state, action) => state,
}, initialState);

function addMessageToChat(state: RootState, action): RootState {
    state.chats.push(action.payload);
    if (state.chats.length >= 50) {
        state.chats.shift();
    }
    return state;
}