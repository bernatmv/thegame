import * as React from 'react';
import MainSectionProps from './mainSectionProps';
import * as style from './mainSection.css';

interface MainSectionState {
    text: string;
}

export class MainSection extends React.Component<MainSectionProps, MainSectionState> {
    static defaultProps = {
        chats: [],
        placeholder: 'Placeholder no traduit',
        text: ''
    };
    
    constructor(props?: MainSectionProps, context?: any) {
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

    render() {
        return (
            <div className={this.props.className}>
                <input className={style.input}
                    type='text'
                    autoFocus
                    placeholder={this.props.placeholder}
                    value={this.state.text}
                    onChange={this._handleChange}
                    onKeyDown={this._handleKeyDown} />
            </div>
        );
    }
}