import * as React from 'react';
import { storiesOf } from '@storybook/react';
import ConnectionStatus from '../src/app/components/common/connectionStatus/connectionStatus';
import ConnectionStatusType from '../src/common/stream/models/connectionStatus';

storiesOf('[COMPONENT] Connection status', module)
    .add('Inline', () => 
        getComponent()
    )
    .add('All statuses', () => 
        getAllVariants()
    )
    .add('In all positions', () => 
        getAllPositions()
    )
    .add('Values varies every 1 second', () => 
        getAnimatingComponent()
    );

function getComponent(props?: any) {
    let defaultProps = {
        connection: {connectionStatus: ConnectionStatusType.Connected, userId: 'Tester-01'},
        top: null,
        bottom: null,
        left: null,
        right: null
    };
    return <ConnectionStatus {...Object.assign({}, defaultProps, props)} />;
}

function getAllVariants() {
    return <div style={{marginLeft: 20, marginTop: 20}}>
        {getComponent({connection: {connectionStatus: ConnectionStatusType.Connected, userId: 'Tester-01'}})}
        <br /><br />
        {getComponent({connection: {connectionStatus: ConnectionStatusType.Connecting, userId: 'Tester-01'}})}
        <br /><br />
        {getComponent({connection: {connectionStatus: ConnectionStatusType.Disconnected, userId: 'Tester-01'}})}
    </div>;
}

function getAllPositions() {
    return <div style={{top: 0, bottom: 0, left: 0, right: 0}}>
        {getComponent({top: true, left: true, connection: {connectionStatus: ConnectionStatusType.Connected, userId: 'Tester-01'}})}
        {getComponent({top: true, right: true, connection: {connectionStatus: ConnectionStatusType.Connected, userId: 'Tester-01'}})}
        {getComponent({bottom: true, left: true, connection: {connectionStatus: ConnectionStatusType.Connected, userId: 'Tester-01'}})}
        {getComponent({bottom: true, right: true, connection: {connectionStatus: ConnectionStatusType.Connected, userId: 'Tester-01'}})}
    </div>;
}

function getAnimatingComponent() {
    return <AnimatingComponent />;
}

class AnimatingComponent extends React.Component<{}, {connectionStatus: ConnectionStatusType}> {
    constructor() {
        super();
        this.state = {connectionStatus: ConnectionStatusType.Connected};
    }

    componentDidMount(): void {
        this.changeActual();
    }

    changeActual = () => {
        setTimeout(() => {
            this.setState({connectionStatus: this.state.connectionStatus === ConnectionStatusType.Connected
                                            ? ConnectionStatusType.Disconnected
                                            : (this.state.connectionStatus === ConnectionStatusType.Disconnected 
                                                ? ConnectionStatusType.Connecting
                                                : ConnectionStatusType.Connected)});
            this.changeActual();
        }, 1000);
    };

    render(): JSX.Element {
        return getComponent({connection: {connectionStatus: this.state.connectionStatus, userId: 'Tester-01'}, top: true, left: true});
    }
}