import { handleActions } from 'redux-actions';
import { debug } from '../../common/service/models/appLogger';
import ChatMessage from './models/chatMessage';
import ConnectedModel from './models/connectedModel';
import ActionsConstants from '../../common/constants/actionsConstants';
import ConnectionStatus from '../../common/stream/models/connectionStatus';
import ConnectionData from '../../common/stream/models/connectionData';
import initialState from './state/initialState';

export default handleActions<ConnectionData, ChatMessage | ConnectedModel>({
    [ActionsConstants.ConnectingToChat]: (state: ConnectionData, action: ReduxActions.Action<void>): ConnectionData => {
        return Object.assign({}, state, { connectionStatus: ConnectionStatus.Connecting});
    },
    [ActionsConstants.ConnectedToChat]: (state: ConnectionData, action: ReduxActions.Action<ConnectedModel>): ConnectionData => {
        return Object.assign({}, state, { userId: action.payload.userId, connectionStatus: ConnectionStatus.Connected});
    },
    [ActionsConstants.DisconnectedFromChat]: (state: ConnectionData, action: ReduxActions.Action<void>): ConnectionData => {
        return Object.assign({}, state, { connectionStatus: ConnectionStatus.Disconnected});
    }
}, initialState.connection);