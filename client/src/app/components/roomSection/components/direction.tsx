import * as React from 'react';
import DirectionProps from './directionProps';
import * as style from './direction.css';

export default class Direction extends React.Component<DirectionProps, {}> {
    static defaultProps = {
        description: '? ? ?'
    };

    _move = () => {
        this.props.move();
    }

    render(): JSX.Element {
        return (
            <div className={style.tile__move} onClick={this._move}>
                <div className={style.tile__move__title}>
                    {this.props.title}
                </div>
                <div className={style.tile__move__description}>
                    {this.props.description}
                </div>
            </div>
        );
    }
}
//add tooltip with hint
//add animation on transition
//add cursor point