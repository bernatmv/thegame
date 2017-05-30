import * as React from 'react';
import LoginSectionProps from './loginSectionProps';
import {Input, Image, Button, Divider} from 'semantic-ui-react';
import TranslationConstants from '../../../common/constants/translationConstants';
import i18nService from '../../../common/service/i18nServiceImpl';
import CreateCharacter from './createCharacter/createCharacter';
import * as style from './loginSection.css';
import * as logoImage from '../../../../assets/images/spider-alt.svg';

export default class LoginSection extends React.Component<LoginSectionProps, LoginSectionState> {
    static defaultProps = {
        placeholder: i18nService.Instance.translate(TranslationConstants.loginInputPlaceholder)
    };

    constructor(props?: LoginSectionProps, context?: any) {
        super(props, context);
        this.state = {
            text: '',
            createCharacter: false,
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

    private _switchPanels = () => {
        this.setState({createCharacter: !this.state.createCharacter});
    }

    private _getLoginPanel(): JSX.Element {
        let input = !this.state.connecting
                    ? <Input 
                            size='medium' autoFocus
                            className={style.login__input__field}
                            placeholder={this.props.placeholder}
                            value={this.state.text}
                            onChange={this._handleChange}
                            onKeyDown={this._handleKeyDown} />
                    : null;
        return <div className={style.login__input}>
                    {input}
                    <Divider 
                        horizontal 
                        inverted 
                        section 
                        style={{fontSize: 11}}>{i18nService.Instance.translate(TranslationConstants.loginDivider)}</Divider>
                    <Button 
                        inverted 
                        basic 
                        color='green' 
                        content={i18nService.Instance.translate(TranslationConstants.signUpButton)} 
                        icon='heart' 
                        labelPosition='left' 
                        size='small' 
                        onClick={this._switchPanels} />
                </div>;
    }

    private _getCreateCharacterPanel(): JSX.Element {
        return <div className={style.signup__body}>
                    <CreateCharacter signup={() => {}} />
                </div>;
    }

    render(): JSX.Element {
        let signup = !!this.state.createCharacter;
        let body = signup ? this._getCreateCharacterPanel() : this._getLoginPanel();
        let size: any = signup ? 'small' : 'medium';
        return (
            <div className={style.login}>
                <div className={signup ? style.signup : style.login__menu}>
                    <div className={signup ? style.signup__logo : style.login__logo}>
                        <Image src={logoImage} size={size} className={signup ? style.signup__logo__main : style.login__logo__main} />
                        <Image src={logoImage} size={size} className={signup ? style.signup__logo__before : style.login__logo__before} />
                        <Image src={logoImage} size={size} className={signup ? style.signup__logo__after : style.login__logo__after} />
                    </div>
                    {body}
                </div>
            </div>
        );
    }
}

interface LoginSectionState {
    text: string;
    createCharacter: boolean;
    connecting: boolean;
}