import ChatMessage from '../../reducers/models/chatMessage';

interface MainSectionProps {
    chats: ChatMessage[];
    sendChat: (chat: ChatMessage) => any;
    placeholder?: string;
    text?: string;
    className?: string;
}
export default MainSectionProps;