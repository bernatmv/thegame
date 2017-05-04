const SendChat = 'SEND_CHAT';
const ReceiveChat = 'ReceiveChat';
const ConnectingToChat = 'ConnectingToChat';
const ConnectedToChat = 'ConnectedToChat';
const DisconnectedFromChat = 'DisconnectedFromChat';
const Move = 'MoveMessage';
const MoveNorth = 'north';
const MoveEast = 'east';
const MoveSouth = 'south';
const MoveWest = 'west';
const MoveUp = 'up';
const MoveDown = 'down';
const LoadRoom = 'LoadRoomMessage';
const Login = 'Login';
const PlayerEntersRoom = 'PlayerEntersRoomMessage';
const PlayerLeavesRoom = 'PlayerLeavesRoomMessage';
const EndIntro = 'EndIntro';

export default class ActionsConstants {
    // CHAT
    public static SendChat: string = SendChat;
    public static ReceiveChat: string = ReceiveChat;
    public static ConnectingToChat: string = ConnectingToChat;
    public static ConnectedToChat: string = ConnectedToChat;
    public static DisconnectedFromChat: string = DisconnectedFromChat;
    // PLAYER
    public static Move: string = Move;
    public static MoveNorth: string = MoveNorth;
    public static MoveEast: string = MoveEast;
    public static MoveSouth: string = MoveSouth;
    public static MoveWest: string = MoveWest;
    public static MoveUp: string = MoveUp;
    public static MoveDown: string = MoveDown;
    // SYSTEM
    public static LoadRoom: string = LoadRoom;
    public static PlayerEntersRoom: string = PlayerEntersRoom;
    public static PlayerLeavesRoom: string = PlayerLeavesRoom;
    public static EndIntro: string = EndIntro;
    // AUTH
    public static Login: string = Login;
}