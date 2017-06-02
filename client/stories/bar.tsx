import * as React from 'react';
import { storiesOf } from '@storybook/react';
import Bar from '../src/app/components/common/bar/bar';

storiesOf('Bar', module)
    .add('Full red', () => 
        getComponent()
    )
    .add('Half-filled green', () => 
        getComponent({actual: 70, color: 'green'})
    )
    .add('Empty', () => 
        getComponent({actual: 0})
    )
    .add('All colors', () => 
        getAllVariants()
    )
    .add('Values varies every 1 second', () => 
        getAnimatingComponent()
    );

function getComponent(props?: any) {
    let defaultProps = {
        title: 'HP',
        actual: 100,
        max: 100,
        color: 'red'
    };
    return <div style={{width: 300, marginLeft: 20, marginTop: 20}}>
            <Bar {...Object.assign({}, defaultProps, props)} />
        </div>;
}

function getAllVariants() {
    return <div>
        {getComponent({color: 'red'})}
        {getComponent({color: 'green'})}
        {getComponent({color: 'blue'})}
        {getComponent({color: 'yellow'})}
    </div>;
}

function getAnimatingComponent() {
    return <AnimatingComponent />;
}

class AnimatingComponent extends React.Component<{}, {actual: number}> {
    constructor() {
        super();
        this.state = {actual: 100};
    }

    componentDidMount(): void {
        this.changeActual();
    }

    changeActual = () => {
        setTimeout(() => {
            this.setState({actual: Math.floor(Math.random() * 100)});
            this.changeActual();
        }, 1000);
    };

    render(): JSX.Element {
        return getComponent({actual: this.state.actual});
    }
}