import * as React from 'react';
import DirectionProps from './directionProps';
import TranslationConstants from '../../../../common/constants/translationConstants';
import i18nService from '../../../../common/service/i18nServiceImpl';
import * as style from './direction.css';

export default class Direction extends React.Component<DirectionProps, {}> {
    static defaultProps = {
        nextRoom: {
            title: i18nService.Instance.translate(TranslationConstants.unvisitedDirection)
        }
    };

    _move = () => {
        this.props.move();
    }

    render(): JSX.Element { //TODO: add animation on transition
        return (
            <div className={style.tile__move} onClick={this._move}>
                <div className={style.tile__move__title}>
                    {this.props.title}
                </div>
                <div className={style.tile__move__description}>
                    {this.props.nextRoom.title}
                </div>                
            </div>
        );
    }
}