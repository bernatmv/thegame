import * as React from 'react';
import ChatMessage from '../../reducers/models/chatMessage';
import ChatSectionProps from './chatSectionProps';
import { Input, List } from 'semantic-ui-react';
import * as style from './chatSection.css';

interface ChatSectionState {
    text: string;
    chats: ChatMessage[];
}

export class ChatSection extends React.Component<ChatSectionProps, ChatSectionState> {
    static defaultProps = {
        chats: [],
        placeholder: 'Say something...',
        text: ''
    };
    
    constructor(props?: ChatSectionProps, context?: any) {
        super(props, context);
        this.state = {
            text: this.props.text || '',
            chats: this.props.chats.reverse()
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

    render() {
        //TODO: add scroll and fix styles
        return (
            <div className={style.container__system__chat}>
                <div className={style.container__system__chat__messages}>
                    <List>
                        {this.state.chats.map((m: ChatMessage, i: number) => <List.Item key={i.toString()}>[{m.sender}] {m.message}</List.Item>)}
                    </List>
                </div>
                <div className={style.container__system__chat__input}>
                    <Input icon='comment' iconPosition='left' size='medium'
                        className={style.container__system__chat__input__field}
                        autoFocus
                        placeholder={this.props.placeholder}
                        value={this.state.text}
                        onChange={this._handleChange}
                        onKeyDown={this._handleKeyDown} />
                </div>
            </div>
        );
    }
}