const SendChat = 'SEND_CHAT';
const ReceiveChat = 'ReceiveChat';
const ConnectingToChat = 'ConnectingToChat';
const ConnectedToChat = 'ConnectedToChat';
const DisconnectedFromChat = 'DisconnectedFromChat';
const Move = 'Move';
const LoadRoom = 'LoadRoom';

export default class ActionsConstants {
    // CHAT
    public static SendChat: string = SendChat;
    public static ReceiveChat: string = ReceiveChat;
    public static ConnectingToChat: string = ConnectingToChat;
    public static ConnectedToChat: string = ConnectedToChat;
    public static DisconnectedFromChat: string = DisconnectedFromChat;
    // PLAYER
    public static Move: string = Move;
    // SYSTEM
    public static LoadRoom: string = LoadRoom;
}