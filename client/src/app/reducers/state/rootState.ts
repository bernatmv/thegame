import ChatMessage from '../models/chatMessage';
import ConnectionStatus from '../../../common/stream/models/connectionStatus';
import RoomModel from '../../../common/service/models/roomModel';

interface RootState {
    chats: ChatMessage[];
    connectionStatus: ConnectionStatus;
    userId: string;
    room: RoomModel;
}

export default RootState;