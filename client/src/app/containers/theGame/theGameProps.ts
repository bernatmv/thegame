import { RouteComponentProps } from 'react-router';
import ChatMessage from '../../reducers/models/chatMessage';
import ConnectionData from '../../../common/stream/models/connectionData';
import RoomModel from '../../../common/service/models/roomModel';
import * as ChatActions from '../../actions/chatActions';
import * as PlayerActions from '../../actions/playerActions';

interface TheGameProps extends RouteComponentProps<void> {
    chats: ChatMessage[];
    connection: ConnectionData;
    room: RoomModel;
    actions: {
        chat: typeof ChatActions;
        player: typeof PlayerActions;
    };
}
export default TheGameProps;