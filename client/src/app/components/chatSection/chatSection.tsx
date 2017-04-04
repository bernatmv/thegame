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

    private _calculateHowLongAgo(received: number): string {
        let elapsed = Date.now() - received;
        if (elapsed < 10 * 1000) { //less than 10 seconds ago
            return 'a moment ago';
        } else if (elapsed < 60 * 1000) { //less than 1 minute ago
            let seconds = Math.floor(elapsed / (1000));
            return seconds + ' seconds ago';
        } else if (elapsed < 60 * 60 * 1000) { //less than 1 hour ago
            let minutes = Math.floor(elapsed / (60 * 1000));
            return minutes + ' minute' + ((minutes > 1) ? 's' : '') + ' ago';
        } else { //in hours
            let hours = Math.floor(elapsed / (60 * 60 * 1000));
            return hours + ' hour' + ((hours > 1) ? 's' : '') + ' ago';            
        }
    }

    private _renderListItem(message: ChatMessage, key: number, arr: ChatMessage[]): JSX.Element {
        if (message.sender === SystemConstants.SystemUser) { //message from the system
            return <Message key={key.toString()} className={style.container__system__chat__messages__item_system}>
                    <Icon name='child' />
                    {message.message}
                </Message>;
        } else if (message.sender === this.props.userId) { //it's me
            let user = (key > 0 && message.sender !== arr[key - 1].sender)
                        ? <div>
                                <span className={style.container__system__chat__messages__item_me}>{message.sender}</span>
                                <span className={style.container__system__chat__messages__item_received}> - {this._calculateHowLongAgo(message.received)}</span>
                            </div>
                        : null;
            return <List.Item key={key.toString()}>
                        {user} {message.message}
                    </List.Item>;
        } else { //it's someone else
            let user = (key > 0 && message.sender !== arr[key - 1].sender)
                        ? <div>
                                <span>{message.sender}</span>
                                <span className={style.container__system__chat__messages__item_received}> - {this._calculateHowLongAgo(message.received)}</span>
                            </div>
                        : null;
            return <List.Item key={key.toString()}>{user} {message.message}</List.Item>;
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
                        {this.props.chats.map((message: ChatMessage, index: number, arr: ChatMessage[]) => this._renderListItem(message, index, arr))}
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