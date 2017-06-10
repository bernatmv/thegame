import * as React from 'react';
import { storiesOf } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import SystemSection from '../src/app/components/systemSection/systemSection';
import ConnectionDataStub from './stubs/connectionDataStub';
import * as style from '../src/app/containers/theGame/theGameContainer.css';

storiesOf('[SECTION] System', module)
    .add('Normal', () => 
        getComponent()
    );

function getComponent(props?: any) {
    let defaultProps = {
        connection: ConnectionDataStub
    };
    return <div className={style.container}>
        <div className={style.container__game} />
        <div className={style.container__system} >
            <div style={{display: 'flex', flex: 1, height: '100%'}} />
            <SystemSection {...Object.assign({}, defaultProps, props)} />
        </div>
    </div>;
}