import * as React from 'react';
import Bar from '../bar/bar';
import * as classnames from 'classnames';
import ProfileProps from './profileProps';
import * as style from './profile.css';

/* tslint:disable */
export default class Profile extends React.Component<ProfileProps, {}> {
    static defaultProps = {
        align: 'left'
    };

    _renderStatusProperty(title: string, color: any, current: number, max: number): JSX.Element {
        return <div className={classnames(style.profile__row)}>
                    <Bar title={title} color={color} actual={current} max={max} />
                </div>;
    }

    render(): JSX.Element {
        let hp = (this.props.hp && !this.props.hp.hide) 
                    ? this._renderStatusProperty('HP', 'red', this.props.hp.current, this.props.hp.max)
                    : null;
        let mp = (this.props.mp && !this.props.mp.hide) 
                    ? this._renderStatusProperty('MP', 'blue', this.props.mp.current, this.props.mp.max)
                    : null;
        let sp = (this.props.sp && !this.props.sp.hide) 
                    ? this._renderStatusProperty('SP', 'yellow', this.props.sp.current, this.props.sp.max)
                    : null;
        let name = (this.props.name)
                    ? <div className={classnames(style.profile__row, style.profile__row_title)}>
                        『<b>{this.props.name}</b>』
                    </div>
                    : null;
        let profession = (this.props.profession) 
                    ? '(' + this.props.profession + ')' 
                    : null;
        return (
            <div className={style.avatar__profile}>
                {name}
                <div className={classnames(style.profile__row, style.profile__row_level)}>
                    「{this.props.race} {profession} ― LV{this.props.level}」
                </div>
                {hp}
                {mp}
                {sp}
            </div>
        );
    }
}