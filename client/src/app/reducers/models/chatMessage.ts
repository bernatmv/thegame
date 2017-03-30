interface ChatMessage {
    sender: string;
    message: string;
    received?: Date;
}
export default ChatMessage;