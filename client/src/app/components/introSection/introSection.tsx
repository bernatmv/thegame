import * as React from 'react';
import IntroSectionProps from './introSectionProps';
import TranslationConstants from '../../../common/constants/translationConstants';
import i18nService from '../../../common/service/i18nServiceImpl';
import appConfig from '../../../config/appConfig';
import * as style from './introSection.css';
import * as classnames from 'classnames';

export default class LoginSection extends React.Component<IntroSectionProps, {}> {
    componentWillMount() {
        setTimeout(() => this.props.endIntro(), appConfig.time.introDuration);
    }

    render(): JSX.Element {
        return (
            <div className={style.intro}>
                <div className={classnames(style.intro__line, 'intro__line-1')}>
                    {i18nService.Instance.translate(TranslationConstants.introLine1)}
                </div>
                <div className={classnames(style.intro__line, 'intro__line-2')}>
                    {i18nService.Instance.translate(TranslationConstants.introLine2)}
                </div>
                <div className={classnames(style.intro__line, 'intro__line-3')}>
                    {i18nService.Instance.translate(TranslationConstants.introLine3)}
                </div>
            </div>
        );
    }
}