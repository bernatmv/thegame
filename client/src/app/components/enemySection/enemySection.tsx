import * as React from 'react';
import Profile from '../common/profile/profile';
import EnemySectionProps from './enemySectionProps';
import * as style from './enemySection.css';

export default class EnemySection extends React.Component<EnemySectionProps, {}> {
    render(): JSX.Element {
        return (
            <div className={style.container__game__enemy}>
                <Profile race={'Goblin'} level={4}
                        hp={{current: 30, total: 30}}
                        mp={{current: 10, total: 10, hide: true}}
                        sp={{current: 20, total: 20, hide: true}}/>
                <Profile race={'Goblin'} level={2}
                        hp={{current: 30, total: 30}}
                        mp={{current: 10, total: 10, hide: true}}
                        sp={{current: 20, total: 20, hide: true}}/>
                <Profile race={'Goblin'} level={2}
                        hp={{current: 30, total: 30}}
                        mp={{current: 10, total: 10, hide: true}}
                        sp={{current: 20, total: 20, hide: true}}/>
                <Profile race={'Goblin'} level={1}
                        hp={{current: 30, total: 30}}
                        mp={{current: 10, total: 10, hide: true}}
                        sp={{current: 20, total: 20, hide: true}}/>
            </div>
        );
    }
}