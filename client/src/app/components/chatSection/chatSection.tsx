import * as React from 'react';
import ChatMessage from '../../reducers/models/chatMessage';
import ChatSectionProps from './chatSectionProps';
import { Input, List } from 'semantic-ui-react';
import ConnectionStatus from '../../../common/stream/models/connectionStatus';
import TranslationConstants from '../../../common/constants/translationConstants';
import i18nService from '../../../common/service/i18nServiceImpl';
import ChatItem from '../common/chatItem/chatItem';
import * as style from './chatSection.css';

interface ChatSectionState {
    text: string;
}

export default class ChatSection extends React.Component<ChatSectionProps, ChatSectionState> {
    static defaultProps = {
        chats: [],
        placeholder: i18nService.Instance.translate(TranslationConstants.saySomething),
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
            return i18nService.Instance.translate(TranslationConstants.connecting);
        } else if (this.props.connectionStatus === ConnectionStatus.Disconnected) {
            return i18nService.Instance.translate(TranslationConstants.offline);
        } else {
            return this.props.placeholder;
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
                        {this.props.chats.map((message: ChatMessage, index: number, arr: ChatMessage[]) => 
                            <ChatItem message={message}
                                        index={index} key={index} 
                                        prevMessage={(index > 0) ? arr[index - 1] : null}
                                        userId={this.props.userId} />)}
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