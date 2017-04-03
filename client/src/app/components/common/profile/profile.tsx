import * as React from 'react';
import { Progress } from 'semantic-ui-react';
import * as classnames from 'classnames';
import ProfileProps from './profileProps';
import * as style from './profile.css';

/* tslint:disable */
export default class Profile extends React.Component<ProfileProps, {}> {
    static defaultProps = {
        align: 'left',
        size: 'medium'
    };
    
    _renderStatusProperty(name: string, color: any, current: number, total: number, size: any): JSX.Element {
        return <div className={classnames(style.profile__row, size)}>
                    <div className={classnames(style.status__row__label)}>{name}</div> 
                    <Progress className={style.status__row__progress} 
                                color={color} 
                                value={current} 
                                total={total} 
                                inverted>{current} / {total}</Progress>
                </div>;
    }

    render(): JSX.Element {
        let size = (this.props.size && this.props.size === 'small')
                    ? style.small
                    : null;
        let hp = (this.props.hp && !this.props.hp.hide) 
                    ? this._renderStatusProperty('HP', 'red', this.props.hp.current, this.props.hp.total, size)
                    : null;
        let mp = (this.props.mp && !this.props.mp.hide) 
                    ? this._renderStatusProperty('MP', 'blue', this.props.mp.current, this.props.mp.total, size)
                    : null;
        let sp = (this.props.sp && !this.props.sp.hide) 
                    ? this._renderStatusProperty('SP', 'yellow', this.props.sp.current, this.props.sp.total, size)
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