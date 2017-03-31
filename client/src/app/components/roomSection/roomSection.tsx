import * as React from 'react';
import RoomSectionProps from './roomSectionProps';
import * as style from './roomSection.css';

export default class RoomSection extends React.Component<RoomSectionProps, {}> {
    render(): JSX.Element {
        return (
            <div className={style.container__game__room}>
            </div>
        );
    }
}