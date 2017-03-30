import { handleActions } from 'redux-actions';
import RootState from './state/rootState';
import ChatMessage from './models/chatMessage';
import SystemConstants from '../../common/constants/systemConstants';
import ActionsConstants from '../../common/constants/actionsConstants';

const initialState: RootState = {
    chats: [{
        sender: SystemConstants.SystemUser,
        message: '',
        received: new Date()
    }]
};

export default handleActions<RootState, ChatMessage>({
    [ActionsConstants.ReceiveChat]: (state, action) => {
        state.chats.push(action.payload);
        return state;
    },
    [ActionsConstants.SendChat]: (state, action) => state,
    [ActionsConstants.ConnectingToChat]: (state, action) => state,
    [ActionsConstants.ConnectedToChat]: (state, action) => state,
    [ActionsConstants.DisconnectedFromChat]: (state, action) => state,
}, initialState);