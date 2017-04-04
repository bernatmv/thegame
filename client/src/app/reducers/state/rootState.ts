import ChatMessage from '../models/chatMessage';
import ConnectionData from '../../../common/stream/models/connectionData';
import RoomModel from '../../../common/service/models/roomModel';

interface RootState {
    chats: ChatMessage[];
    connection: ConnectionData;
    room: RoomModel;
}

export default RootState;