interface WebsocketMessage {
    kind: string;
    sender: string;
    message: string;
    received?: string;
}
export default WebsocketMessage;