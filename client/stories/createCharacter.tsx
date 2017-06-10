import * as React from 'react';
import { storiesOf } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import CreateCharacter from '../src/app/components/loginSection/createCharacter/createCharacter';
import * as style from '../src/app/containers/theGame/theGameContainer.css';

storiesOf('[SECTION] Create character', module)
    .add('Normal', () => 
        getComponent()
    );

function getComponent(props?: any) {
    let defaultProps = {
        signup: action('signup')
    };
    return <div className={style.container}>
        <CreateCharacter {...defaultProps} />
    </div>;
}