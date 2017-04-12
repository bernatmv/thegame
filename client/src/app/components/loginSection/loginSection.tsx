import * as React from 'react';
import LoginSectionProps from './loginSectionProps';
import { Input, Image } from 'semantic-ui-react';
import ConnectionStatus from '../common/connectionStatus/connectionStatus';
import * as style from './loginSection.css';
import * as logoImage from '../../../../assets/images/spider-alt.svg';

interface LoginSectionState {
    text: string;
}

export default class ChatSection extends React.Component<LoginSectionProps, LoginSectionState> {
    static defaultProps = {
        placeholder: '¿Cómo te llamas?'
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
                <ConnectionStatus connection={this.props.connection} top right />
                <div className={style.login__menu}>
                    <div className={style.login__logo}>
                        <Image src={logoImage} size={'medium'} className={style.login__logo__main} />
                        <Image src={logoImage} size={'medium'} className={style.login__logo__before} />
                        <Image src={logoImage} size={'medium'} className={style.login__logo__after} />
                    </div>
                    <div className={style.login__input}>
                        <Input size='medium' autoFocus
                            className={style.login__input__field}
                            placeholder={this.props.placeholder}
                            value={this.state.text}
                            onChange={this._handleChange}
                            onKeyDown={this._handleKeyDown} />
                    </div>
                </div>
            </div>
        );
    }
}