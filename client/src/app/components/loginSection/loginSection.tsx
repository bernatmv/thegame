import * as React from 'react';
import LoginSectionProps from './loginSectionProps';
import { Input } from 'semantic-ui-react';
import ConnectionStatus from '../../../common/stream/models/connectionStatus';
import * as style from './loginSection.css';

interface LoginSectionState {
    text: string;
}

export default class ChatSection extends React.Component<LoginSectionProps, LoginSectionState> {
    static defaultProps = {
        placeholder: 'Nombre de usuario...'
    };

    constructor(props?: LoginSectionProps, context?: any) {
        super(props, context);
        this.state = {
            text: ''
        };
    }

    private _handleChange = (e) => {
        this.setState({ text: e.target.value });
    }

    private _handleKeyDown = (e) => {
        const text = e.target.value.trim();
        if (e.which === 13) { //ENTER key
            this.props.login(text);
            this.setState({ text: '' });
        }
    }

    render(): JSX.Element {
        return (
            <div className={style.login}>
                <div className={style.container__system__chat__input}>
                    <Input size='medium' autoFocus
                        className={style.login__input__field}
                        placeholder={this.props.placeholder}
                        value={this.state.text}
                        onChange={this._handleChange}
                        onKeyDown={this._handleKeyDown} />
                </div>
            </div>
        );
    }
}