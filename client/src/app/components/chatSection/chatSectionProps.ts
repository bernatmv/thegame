import ChatMessage from '../../reducers/models/chatMessage';
import ConnectionStatus from '../../../common/stream/models/connectionStatus';

interface ChatSectionProps {
    chats: ChatMessage[];
    sendChat: (chat: ChatMessage) => any;
    connectionStatus: ConnectionStatus;
    userId: string;
    placeholder?: string;
    text?: string;
}
export default ChatSectionProps;