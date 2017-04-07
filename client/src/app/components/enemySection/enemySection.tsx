import * as React from 'react';
import Profile from '../common/profile/profile';
import EnemySectionProps from './enemySectionProps';
import * as style from './enemySection.css';

export default class EnemySection extends React.Component<EnemySectionProps, {}> {
    render(): JSX.Element {
        let enemies = this.props.room.enemies;
        return (
            <div className={style.container__game__enemy}>
                {enemies.map((enemy, i) => <Profile key={i}
                                                race={enemy.profile.race} 
                                                level={enemy.profile.level}
                                                hp={enemy.profile.hp}
                                                mp={enemy.profile.mp}
                                                sp={enemy.profile.sp} />)}
            </div>
        );
    }
}