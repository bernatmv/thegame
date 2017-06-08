import * as React from 'react';
import { storiesOf } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import RoomFooter from '../src/app/components/roomSection/components/roomFooter';
import RoomStub from './stubs/roomStub';
import * as style from '../src/app/containers/theGame/theGameContainer.css';

storiesOf('[COMPONENT] Footer', module)
    .add('Normal', () => 
        getComponent()
    );

function getComponent(props?: any) {
    let defaultProps = {
        room: RoomStub
    };
    return <div style={{width: 500, marginLeft: 20, marginTop: 20}}>
        <RoomFooter {...Object.assign({}, defaultProps, props)} />
    </div>;
}
