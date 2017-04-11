import * as React from 'react';
import { Message } from 'semantic-ui-react';
import ConnectionStatus from '../common/connectionStatus/connectionStatus';
import SystemSectionProps from './systemSectionProps';
import * as style from './systemSection.css';

export default class ChatSection extends React.Component<SystemSectionProps, {}> {
    render(): JSX.Element {
        return (
            <div className={style.container__system__display}>
                <Message info className={style.container__system__info}>
                    <Message.Header>System display</Message.Header>
                    <p>Here we will be able to see the inventory and some skills and menu options</p>
                </Message>
                <ConnectionStatus connection={this.props.connection} bottom />
            </div>
        );
    }
}