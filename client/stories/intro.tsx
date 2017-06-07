import * as React from 'react';
import { storiesOf } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import IntroSection from '../src/app/components/introSection/introSection';
import * as style from '../src/app/containers/theGame/theGameContainer.css';

storiesOf('[SECTION] Intro', module)
    .add('Normal', () => 
        getComponent()
    );

function getComponent(props?: any) {
    let defaultProps = {
        endIntro: action('end intro')
    };
    return <IntroSection {...defaultProps} />;
}