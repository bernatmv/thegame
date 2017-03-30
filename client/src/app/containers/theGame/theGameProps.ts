import { RouteComponentProps } from 'react-router';
import ChatMessage from '../../reducers/models/chatMessage';
import * as ChatActions from '../../actions/chatActions';

interface TheGameProps extends RouteComponentProps<void> {
    chats: ChatMessage[];
    actions: typeof ChatActions;
}
export default TheGameProps;