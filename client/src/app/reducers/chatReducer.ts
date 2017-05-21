import { handleActions } from 'redux-actions';
import { debug } from '../../common/service/models/appLogger';
import ChatMessage from './models/chatMessage';
import ConnectedModel from './models/connectedModel';
import ActionsConstants from '../../common/constants/actionsConstants';
import initialState from './state/initialState';

const MAX_MESSAGES = 100;

export default handleActions<ChatMessage[], ChatMessage>({
    [ActionsConstants.ReceiveChat]: (state: ChatMessage[], action: ReduxActions.Action<ChatMessage>): ChatMessage[] => {
        return addMessageToChat(state, action.payload);
    },
    [ActionsConstants.SendChat]: (state: ChatMessage[], action: ReduxActions.Action<ChatMessage>): ChatMessage[] => {
        return addMessageToChat(state, Object.assign({}, action.payload, { received: Date.now() }));
    }
}, initialState.chats);

function addMessageToChat(chats: Array<ChatMessage>, message: ChatMessage): Array<ChatMessage> {
    return [
        ...chats,
        message
    ].slice(-MAX_MESSAGES);
}