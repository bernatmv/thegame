import * as React from 'react';
import LoginSectionProps from './loginSectionProps';
import { Input, Image } from 'semantic-ui-react';
import TranslationConstants from '../../../common/constants/translationConstants';
import i18nService from '../../../common/service/i18nServiceImpl';
import * as style from './loginSection.css';
import * as logoImage from '../../../../assets/images/spider-alt.svg';

interface LoginSectionState {
    text: string;
    connecting: boolean;
}

export default class LoginSection extends React.Component<LoginSectionProps, LoginSectionState> {
    static defaultProps = {
        placeholder: i18nService.Instance.translate(TranslationConstants.loginInputPlaceholder)
    };

    constructor(props?: LoginSectionProps, context?: any) {
        super(props, context);
        this.state = {
            text: '',
            connecting: false
        };
    }

    private _handleChange = (e) => {
        this.setState({ text: e.target.value });
    }

    private _handleKeyDown = (e) => {
        const text = e.target.value.trim();
        if (e.which === 13) { //ENTER key
            this.props.login(text);
            this.setState({ connecting: true });
        }
    }

    render(): JSX.Element {
        let input = !this.state.connecting
                    ? <Input size='medium' autoFocus
                            className={style.login__input__field}
                            placeholder={this.props.placeholder}
                            value={this.state.text}
                            onChange={this._handleChange}
                            onKeyDown={this._handleKeyDown} />
                    : null;
        return (
            <div className={style.login}>
                <div className={style.login__menu}>
                    <div className={style.login__logo}>
                        <Image src={logoImage} size={'medium'} className={style.login__logo__main} />
                        <Image src={logoImage} size={'medium'} className={style.login__logo__before} />
                        <Image src={logoImage} size={'medium'} className={style.login__logo__after} />
                    </div>
                    <div className={style.login__input}>
                        {input}
                    </div>
                </div>
            </div>
        );
    }
}