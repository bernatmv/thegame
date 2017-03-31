import { createAction } from 'redux-actions';
import ActionsConstants from '../../common/constants/actionsConstants';
import ChatMessage from '../reducers/models/chatMessage';
import ConnectedModel from '../reducers/models/connectedModel';

export const sendChat = createAction<ChatMessage>(ActionsConstants.SendChat);
export const receiveChat = createAction<ChatMessage>(ActionsConstants.ReceiveChat);
export const connectingToChat = createAction<void>(ActionsConstants.ConnectingToChat);
export const connectedToChat = createAction<ConnectedModel>(ActionsConstants.ConnectedToChat);
export const disconnectedFromChat = createAction<void>(ActionsConstants.DisconnectedFromChat);