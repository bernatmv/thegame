import * as React from 'react';
import { storiesOf } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import Profile from '../src/app/components/common/profile/profile';

storiesOf('[COMPONENT] Profile', module)
    .add('Normal', () => 
        getComponent()
    )
    .add('Just required info', () => 
        getComponent({name: null, profession: null, hp: {current: 30, max: 100}, mp: null, sp: null})
    );

function getComponent(props?: any) {
    let defaultProps = {
        name: 'Watarhu',
        race: 'Human',
        profession: 'Wizard',
        level: 100,
        hp: {current: 1000, max: 1000},
        mp: {current: 99999, max: 99999},
        sp: {current: 1000, max: 1000}
    };
    return <div style={{width: 300, marginLeft: 20, marginTop: 20}}>
            <Profile {...Object.assign({}, defaultProps, props)} />
        </div>;
}