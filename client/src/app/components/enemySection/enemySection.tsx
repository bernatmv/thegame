import * as React from 'react';
import EnemySectionProps from './enemySectionProps';
import * as style from './enemySection.css';

export default class EnemySection extends React.Component<EnemySectionProps, {}> {
    render(): JSX.Element {
        return (
            <div className={style.container__game__enemy}>
            </div>
        );
    }
}