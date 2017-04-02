import * as React from 'react';
import { Progress, Label } from 'semantic-ui-react';
import * as classnames from 'classnames';
import ProfileProps from './profileProps';
import * as style from './profile.css';

export default class Profile extends React.Component<ProfileProps, {}> {
    
    _renderStatusProperty(name: string, color: any, current: number, total: number, size: any): JSX.Element {
        return <div className={classnames(style.profile__row, size)}>
                    <div className={classnames(style.status__row__label, size)}>{name}</div> 
                    <Progress className={style.status__row__progress} 
                                color={color} 
                                value={current} 
                                total={total} 
//                                progress={'ratio'} 
                                inverted={'true'}
                                size={this.props.size}>{current} / {total}</Progress>
                </div>;
    }

    render(): JSX.Element {
        let size = (this.props.size && this.props.size === 'small')
                    ? style.small
                    : style.normal;
        let hp = (this.props.hp && !this.props.hp.hide) 
                    ? this._renderStatusProperty('HP', 'red', this.props.hp.current, this.props.hp.total, size)
                    : null;
        let mp = (this.props.mp && !this.props.mp.hide) 
                    ? this._renderStatusProperty('MP', 'blue', this.props.mp.current, this.props.mp.total, size)
                    : null;
        let sp = (this.props.sp && !this.props.sp.hide) 
                    ? this._renderStatusProperty('SP', 'yellow', this.props.sp.current, this.props.sp.total, size)
                    : null;
        return (
            <div className={style.avatar__profile}>
                <div className={classnames(style.profile__row, style.profile__row_title, size)}>
                    『<b>{this.props.name}</b>』
                </div>
                <div className={classnames(style.profile__row, style.profile__row_level, size)}>
                    「{this.props.race} ({this.props.profession}) ― LV{this.props.level}」
                </div>
                {hp}
                {mp}
                {sp}
            </div>
        );
    }
}