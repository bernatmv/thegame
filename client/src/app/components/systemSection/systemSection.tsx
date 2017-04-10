import * as React from 'react';
import { Message, Label, Icon } from 'semantic-ui-react';
import ConnectionStatus from '../../../common/stream/models/connectionStatus';
import SystemSectionProps from './systemSectionProps';
import * as style from './systemSection.css';

export default class ChatSection extends React.Component<SystemSectionProps, {}> {
    private _getConnectionStatus(connectionStatus: ConnectionStatus): JSX.Element {
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
        return <Label color={color} image className={style.container__system__connectionStatus}>
                    <Icon name={icon} loading={loading} />
                    {'Servidor'}
                    <Label.Detail>{label}</Label.Detail>
                </Label>;
    }
    
    render(): JSX.Element {
        return (
            <div className={style.container__system__display}>
                <Message info className={style.container__system__info}>
                    <Message.Header>System display</Message.Header>
                    <p>Here we will be able to see the inventory and some skills and menu options</p>
                </Message>
                {this._getConnectionStatus(this.props.connection.connectionStatus)}
            </div>
        );
    }
}