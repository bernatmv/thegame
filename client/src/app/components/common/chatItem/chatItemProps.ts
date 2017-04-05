import ChatMessage from '../../../reducers/models/chatMessage';

interface ChatItemProps {
    message: ChatMessage;
    prevMessage?: ChatMessage;
    index: number;
    userId: string;
}
export default ChatItemProps;