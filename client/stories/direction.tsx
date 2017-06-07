import * as React from 'react';
import { storiesOf } from '@storybook/react';
import { action } from '@storybook/addon-actions';
import Direction from '../src/app/components/roomSection/components/direction';

storiesOf('[COMPONENT] Direction', module)
  .add('Normal', () => 
    getComponent()
  );

function getComponent(props?: any) {
  let defaultProps = {
    title: 'North',
    move: () => action('move')()
  };
  return <div style={{width: 300}}>
    <Direction {...Object.assign({}, defaultProps, props)} />
  </div>;
}