import * as React from 'react';
import { Label, Icon } from 'semantic-ui-react';
import ConnectionStatus from '../../../../common/stream/models/connectionStatus';
import ConnectionStatusProps from './connectionStatusProps';
import * as style from './connectionStatus.css';

export default class ChatSection extends React.Component<ConnectionStatusProps, {}> {
    render(): JSX.Element {
        let connectionStatus = this.props.connection.connectionStatus;
        let color, label, icon;
        let loading = false;
        if (connectionStatus === ConnectionStatus.Connected) {
            color = 'teal';
            icon = 'thumbs outline up';
            label = 'Conectado';
        } else if (connectionStatus === ConnectionStatus.Connecting) {
            color = 'yellow';
            icon = 'circle notched';
            label = 'Conectando...';
            loading = true;
        } else if (connectionStatus === ConnectionStatus.Disconnected) {
            color = 'red';
            icon = 'thumbs outline down';
            label = 'Desconectado';
        }
        return <Label color={color} image className={style.connectionStatus}>
                    <Icon name={icon} loading={loading} />
                    {'Servidor'}
                    <Label.Detail>{label}</Label.Detail>
                </Label>;
    }
}