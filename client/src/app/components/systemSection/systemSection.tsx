import * as React from 'react';
import ConnectionStatus from '../common/connectionStatus/connectionStatus';
import SystemSectionProps from './systemSectionProps';
import * as style from './systemSection.css';

export default class ChatSection extends React.Component<SystemSectionProps, {}> {
    render(): JSX.Element {
        return (
            <div className={style.container__system__display}>
                <ConnectionStatus connection={this.props.connection} bottom right />
            </div>
        );
    }
}