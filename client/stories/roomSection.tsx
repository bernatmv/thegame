import * as React from 'react';
import { storiesOf } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import RoomSection from '../src/app/components/roomSection/roomSection';
import RoomStub from './stubs/roomStub';
import * as style from '../src/app/containers/theGame/theGameContainer.css';

storiesOf('[SECTION] Room', module)
    .add('Normal', () => 
        getComponent()
    );

function getComponent(props?: any) {
    let defaultProps = {
        room: RoomStub,
        move: action('move')
    };
    return <div className={style.container}>
        <div className={style.container__game}>
            <div style={{display: 'flex', flex: 1, width: 200, height: '100%'}} />
            <RoomSection {...Object.assign({}, defaultProps, props)} />
            <div style={{display: 'flex', flex: 1, width: 200, height: '100%'}} />
        </div>
        <div className={style.container__system} />
    </div>;
}
