import RootState from './rootState';
import SystemConstants from '../../../common/constants/systemConstants';
import ConnectionStatus from '../../../common/stream/models/connectionStatus';

let initialState: RootState = {
    chats: [{
        sender: SystemConstants.SystemUser,
        message: 'Welcome to TheGame!',
        received: new Date()
    }],
    connectionStatus: ConnectionStatus.Connecting,
    userId: '',
    room: null
};
export default initialState;