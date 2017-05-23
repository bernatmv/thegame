import * as React from 'react';
import * as classnames from 'classnames';
import BarProps from './barProps';
import * as style from './bar.css';

/* tslint:disable */
export default class Bar extends React.Component<BarProps, {}> {
    private _getBarColor(color: 'red' | 'yellow' | 'blue' | 'green'): string {
        if (color === 'red') {
            return style.red;
        } else if (color === 'yellow') {
            return style.yellow;
        } else if (color === 'blue') {
            return style.blue;
        } else if (color === 'green') {
            return style.green;
        } else {
            return style.gold;
        }
    }

    render(): JSX.Element {
        let color = this._getBarColor(this.props.color);
        let width = Math.floor((this.props.actual / this.props.max) * 100);
        return (
            <div className={style.bar__container}>
                <div className={style.progressbar}>
                    <div className={classnames(style.bar, color)} style={{width: width + '%'}}><span></span></div>
                </div>
                <div className={style.bar__text}>
                    <div className={style.bar__text_left}>{this.props.title}</div>
                    <div className={style.bar__text_right}>{this.props.actual} / {this.props.max}</div>
                </div>
            </div>
        );
    }
}