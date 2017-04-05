import * as React from 'react';
import RoomModel from '../../../common/service/models/roomModel';
import RoomFooter from './components/roomFooter';
import RoomSectionProps from './roomSectionProps';
import Direction from './components/direction';
import ActionsConstants from '../../../common/constants/actionsConstants';
import * as classnames from 'classnames';
import * as style from './roomSection.css';

export default class RoomSection extends React.Component<RoomSectionProps, {}> {
    _buildRoom(room: RoomModel): JSX.Element {
        return <div className={style.tile__room}>
                    <div className={style.room__header}>
                        {false ? <div className={style.room__header__id}>{room.id}</div> : null}
                        <div className={style.room__header__title}>
                            {room.title}
                        </div>
                    </div>
                    <div className={style.room__description}>
                        <div className={style.room__description__body}>
                            {room.description}
                        </div>
                    </div>
                    <RoomFooter room={room} />
                </div>;
    }

    render(): JSX.Element {
        let exits = this.props.room.exits;
        let north = (exits.north) ? <Direction title={'North'} move={() => this.props.move(ActionsConstants.MoveNorth)} /> : null;
        let east = (exits.east) ? <Direction title={'East'} move={() => this.props.move(ActionsConstants.MoveEast)} /> : null;
        let south = (exits.south) ? <Direction title={'South'} move={() => this.props.move(ActionsConstants.MoveSouth)} /> : null;
        let west = (exits.west) ? <Direction title={'West'} move={() => this.props.move(ActionsConstants.MoveWest)} /> : null;
        let up = (exits.up) ? <Direction title={'Up'} move={() => this.props.move(ActionsConstants.MoveUp)} /> : null;
        let down = (exits.down) ? <Direction title={'Down'} move={() => this.props.move(ActionsConstants.MoveDown)} /> : null;

        return (
            <div className={style.room}>
                <div className={style.row}>
                    <div className={style.row__column} />
                    <div className={style.row__column} />
                    <div className={style.row__column}>
                        {north}
                    </div>
                    <div className={style.row__column}>
                        {up}
                    </div>
                    <div className={style.row__column} />
                </div>
                <div className={classnames(style.row, style.row__middle)}>
                    <div className={style.row__column}>
                        <div className={style.row__middle__lateral} />
                        <div className={style.row__middle__lateral}>
                            {west}
                        </div>
                        <div className={style.row__middle__lateral} />
                    </div>
                    <div className={classnames(style.row__column, style.row__column__middle)}>
                        {this._buildRoom(this.props.room)}
                    </div>
                    <div className={style.row__column}>
                        <div className={style.row__middle__lateral} />
                        <div className={style.row__middle__lateral}>
                            {east}
                        </div>
                        <div className={style.row__middle__lateral} />
                    </div>
                </div>
                <div className={style.row}>
                    <div className={style.row__column} />
                    <div className={style.row__column} />
                    <div className={style.row__column}>
                        {south}
                    </div>
                    <div className={style.row__column}>
                        {down}
                    </div>
                    <div className={style.row__column} />
                </div>
            </div>
        );
    }
}