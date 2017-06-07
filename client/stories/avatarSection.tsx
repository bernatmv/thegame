import * as React from 'react';
import { storiesOf } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import AvatarSection from '../src/app/components/avatarSection/avatarSection';
import RoomStub from './stubs/roomStub';
import {playerStubFactory} from '../__tests__/stubs/playerStub';
import * as style from '../src/app/containers/theGame/theGameContainer.css';

storiesOf('[SECTION] Avatar', module)
    .add('Normal', () => 
        getComponent()
    );

function getComponent(props?: any) {
    let defaultProps = {
        room: RoomStub,
        player: playerStubFactory('Tester-01')
    };
    return <div className={style.container}>
        <div className={style.container__game}>
            <AvatarSection {...defaultProps} />
            <div style={{display: 'flex', flex: 4, width: 200, height: '100%'}} />
            <div style={{display: 'flex', flex: 1, width: 200, height: '100%'}} />
        </div>
        <div className={style.container__system} />
    </div>;
}
