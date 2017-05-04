import ChatMessage from '../models/chatMessage';
import ConnectionData from '../../../common/stream/models/connectionData';
import RoomModel from '../../../common/service/models/roomModel';
import PlayerModel from '../../../common/service/models/playerModel';
import SystemModel from '../../../common/service/models/systemModel';

interface RootState {
    chats: ChatMessage[];
    connection: ConnectionData;
    room: RoomModel;
    player: PlayerModel;
    system: SystemModel;
}

export default RootState;