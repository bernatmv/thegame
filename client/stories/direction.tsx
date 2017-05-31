import * as React from 'react';
import { storiesOf } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import Direction from '../src/app/components/roomSection/components/direction';

storiesOf('Direction', module)
  .add('Normal', () => 
    getComponent()
  );

function getComponent(props?: any) {//TODO: PROPS type
  let defaultProps = {
    title: 'North',
    move: () => action('move')
  };
  return <Direction {...Object.assign({}, defaultProps, props)} />;
}