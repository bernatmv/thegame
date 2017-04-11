import * as React from 'react';
import { Label, Icon } from 'semantic-ui-react';
import ConnectionStatus from '../../../../common/stream/models/connectionStatus';
import ConnectionStatusProps from './connectionStatusProps';
import * as classnames from 'classnames';
import * as style from './connectionStatus.css';

export default class ChatSection extends React.Component<ConnectionStatusProps, {}> {
    render(): JSX.Element {
        let classes = classnames(
            this.props.top ? style.top : null,
            this.props.bottom ? style.bottom : null,
            this.props.left ? style.left : null,
            this.props.right ? style.right : null,
            style.connectionStatus);
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
        return <Label color={color} image className={classes}>
                    <Icon name={icon} loading={loading} />
                    {'Servidor'}
                    <Label.Detail>{label}</Label.Detail>
                </Label>;
    }
}