interface WebsocketMessage {
    sender: string;
    message: string;
    received?: string;
}
export default WebsocketMessage;