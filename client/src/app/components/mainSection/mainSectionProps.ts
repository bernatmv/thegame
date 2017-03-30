import ChatMessage from '../../reducers/models/chatMessage';

interface MainSectionProps {
    chats: ChatMessage[];
    sendChat: (chat: ChatMessage) => any;
    placeholder?: string;
    text?: string;
}
export default MainSectionProps;