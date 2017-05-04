import * as React from 'react';
import IntroSectionProps from './introSectionProps';
import TranslationConstants from '../../../common/constants/translationConstants';
import i18nService from '../../../common/service/i18nServiceImpl';
import appConfig from '../../../config/appConfig';
import * as style from './introSection.css';
import * as classnames from 'classnames';

export default class LoginSection extends React.Component<IntroSectionProps, {step: number;}> {
    constructor() {
        super();
        this.state = {
            step: 0
        };
    }

    componentWillMount() {
        setTimeout(() => this.props.endIntro(), appConfig.time.introDuration);
        setTimeout(() => this.setState({step: this.state.step + 1}), 2150);
        setTimeout(() => this.setState({step: this.state.step + 1}), 4300);
        setTimeout(() => this.setState({step: 100}), 8500);
    }

    render(): JSX.Element {
        let getFade = (limit) => ((this.state.step >= limit) ? ((this.state.step >= 100) ? style.fadeout : style.fadein) : style.hidden);

        return (
            <div className={style.intro}>
                <div className={classnames(style.intro__line, 'intro__line-1', getFade(0))}>
                    {i18nService.Instance.translate(TranslationConstants.introLine1)}
                </div>
                <div className={classnames(style.intro__line, 'intro__line-2', getFade(1))}>
                    {i18nService.Instance.translate(TranslationConstants.introLine2)}
                </div>
                <div className={classnames(style.intro__line, 'intro__line-3', getFade(2))}>
                    {i18nService.Instance.translate(TranslationConstants.introLine3)}
                </div>
            </div>
        );
    }
}