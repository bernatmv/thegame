import { RouteComponentProps } from 'react-router';
import ChatMessage from '../../reducers/models/chatMessage';
import ConnectionData from '../../../common/stream/models/connectionData';
import RoomModel from '../../../common/service/models/roomModel';
import PlayerModel from '../../../common/service/models/playerModel';
import * as ChatActions from '../../actions/chatActions';
import * as PlayerActions from '../../actions/playerActions';
import * as AuthActions from '../../actions/authActions';

interface TheGameProps extends RouteComponentProps<void> {
    chats: ChatMessage[];
    connection: ConnectionData;
    room: RoomModel;
    player: PlayerModel;
    actions: {
        chat: typeof ChatActions;
        player: typeof PlayerActions;
        auth: typeof AuthActions;
    };
}
export default TheGameProps;