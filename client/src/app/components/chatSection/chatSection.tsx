import * as React from 'react';
import ChatMessage from '../../reducers/models/chatMessage';
import ChatSectionProps from './chatSectionProps';
import { Input, List, Message, Icon } from 'semantic-ui-react';
import ConnectionStatus from '../../../common/stream/models/connectionStatus';
import SystemConstants from '../../../common/constants/systemConstants';
import * as style from './chatSection.css';

interface ChatSectionState {
    text: string;
}

export default class ChatSection extends React.Component<ChatSectionProps, ChatSectionState> {
    static defaultProps = {
        chats: [],
        placeholder: 'Say something...',
        text: '',
        connecting: false
    };
    
    constructor(props?: ChatSectionProps, context?: any) {
        super(props, context);
        this.state = {
            text: this.props.text || ''
        };
    }

    private _handleChange = (e) => {
        this.setState({ text: e.target.value });
    }

    private _handleKeyDown = (e) => {
        const text = e.target.value.trim();
        if (e.which === 13) { //ENTER key
            this.props.sendChat({
                sender: null, // this will be intercepted and filled by the websocketMiddleware
                message: text
            });
            this.setState({ text: '' });
        }
    }

    private _getPlaceholder(status: ConnectionStatus): string {
        if (this.props.connectionStatus === ConnectionStatus.Connecting) {
            return 'Connecting...';
        } else if (this.props.connectionStatus === ConnectionStatus.Disconnected) {
            return 'Offline';
        } else {
            return this.props.placeholder;
        }
    }

    private _renderListItem(message: ChatMessage, key: number): JSX.Element {
        if (message.sender === SystemConstants.SystemUser) { //message from the system
            return <Message key={key.toString()} className={style.container__system__chat__messages__item_system}>
                    <Icon name='child' />
                    {message.message}
                </Message>;
        } else if (message.sender === this.props.userId) { //it's me
            return <List.Item key={key.toString()}>
                        <span className={style.container__system__chat__messages__item_me}>[{message.sender}]</span> {message.message}
                    </List.Item>;
        } else { //it's someone else
            return <List.Item key={key.toString()}>[{message.sender}] {message.message}</List.Item>;
        }
    }

    render(): JSX.Element {
        let placeholder = this._getPlaceholder(this.props.connectionStatus);
        let loading = (this.props.connectionStatus === ConnectionStatus.Connecting);
        let icon = (this.props.connectionStatus === ConnectionStatus.Connected)
                    ? 'comment outline'
                    : 'warning sign';
        return (
            <div className={style.container__system__chat}>
                <div className={style.container__system__chat__messages}>
                    <List>
                        {this.props.chats.map((m: ChatMessage, i: number) => this._renderListItem(m, i))}
                    </List>
                </div>
                <div className={style.container__system__chat__input}>
                    <Input icon={icon} iconPosition='left' size='medium' autoFocus
                        className={style.container__system__chat__input__field}
                        loading={loading}
                        placeholder={placeholder}
                        value={this.state.text}
                        onChange={this._handleChange}
                        onKeyDown={this._handleKeyDown} />
                </div>
            </div>
        );
    }
}