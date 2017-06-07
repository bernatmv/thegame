import * as React from 'react';
import { storiesOf } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import LoginSection from '../src/app/components/loginSection/loginSection';
import * as style from '../src/app/containers/theGame/theGameContainer.css';

storiesOf('[SECTION] Login', module)
    .add('Normal', () => 
        getComponent()
    );

function getComponent(props?: any) {
    let defaultProps = {
        placeholder: 'Who are you?',
        login: action('login')
    };
    return <div className={style.container}>
        <LoginSection {...defaultProps} />
    </div>;
}