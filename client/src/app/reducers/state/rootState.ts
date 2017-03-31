import ChatMessage from '../models/chatMessage';
import ConnectionStatus from '../../../common/stream/models/connectionStatus';

interface RootState {
    chats: ChatMessage[];
    connectionStatus: ConnectionStatus;
    userId: string;
}

export default RootState;