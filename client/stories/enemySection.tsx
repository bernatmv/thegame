import * as React from 'react';
import { storiesOf } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import EnemySection from '../src/app/components/enemySection/enemySection';
import RoomStub from './stubs/roomStub';
import * as style from '../src/app/containers/theGame/theGameContainer.css';

storiesOf('[SECTION] Enemy', module)
    .add('Normal', () => 
        getComponent()
    );

function getComponent(props?: any) {
    let defaultProps = {
        room: RoomStub
    };
    return <div className={style.container}>
        <div className={style.container__game}>
            <div style={{display: 'flex', flex: 1, width: 200, height: '100%'}} />
            <div style={{display: 'flex', flex: 4, width: 200, height: '100%'}} />
            <EnemySection {...defaultProps} />
        </div>
        <div className={style.container__system} />
    </div>;
}
