import ChatMessage from '../../reducers/models/chatMessage';

interface ChatSectionProps {
    chats: ChatMessage[];
    sendChat: (chat: ChatMessage) => any;
    placeholder?: string;
    text?: string;
}
export default ChatSectionProps;