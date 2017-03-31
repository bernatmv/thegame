import { RouteComponentProps } from 'react-router';
import ChatMessage from '../../reducers/models/chatMessage';
import ConnectionStatus from '../../../common/stream/models/connectionStatus';
import * as ChatActions from '../../actions/chatActions';

interface TheGameProps extends RouteComponentProps<void> {
    chats: ChatMessage[];
    connectionStatus: ConnectionStatus;
    userId: string;
    actions: typeof ChatActions;    
}
export default TheGameProps;