import * as React from 'react';
import EnemyModel from '../../../common/service/models/enemyModel';
import ItemModel from '../../../common/service/models/itemModel';
import NPCModel from '../../../common/service/models/npcModel';
import RoomModel from '../../../common/service/models/roomModel';
import RoomSectionProps from './roomSectionProps';
import * as classnames from 'classnames';
import * as style from './roomSection.css';

export default class RoomSection extends React.Component<RoomSectionProps, {}> {
    _buildRoomFooter(room: RoomModel): JSX.Element {
        let enemies:JSX.Element = null;
        let npc:JSX.Element = null;
        let items:JSX.Element = null;
        if (room.items.length === 0) {
            items = <div>{'No hay objetos cerca'}</div>;
        } else {
            items = <div className={style.room__footer__positive}>
                        {'Hay ' + room.items.map((item: ItemModel) => item.id).join(', ')}
                    </div>;
        }
        if (room.enemies.length === 0) {
            enemies = <div>{'No ves enemigos'}</div>;
        } else {
            enemies = <div className={style.room__footer__positive}>
                            {'Ves ' + room.enemies.map((enemy: EnemyModel) => enemy.id).join(', ')}
                        </div>;
        }
        if (room.npc.length > 0) {
            npc = <div className={style.room__footer__positive}>
                        {room.npc.map((npc: NPCModel) => <div>{npc.id + ' está aquí'}</div>)}
                    </div>;
        }
        return <div>
                    {npc}
                    {enemies}
                    {items}
                </div>;
    }

    _buildDirection(title: string, description: string): JSX.Element {
        return <div className={style.tile__move}>
                    <div className={style.tile__move__title}>
                        North
                    </div>
                    <div className={style.tile__move__description}>
                        ? ? ?
                    </div>
                </div>;
    }

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
                    <div className={style.room__footer}>
                        {this._buildRoomFooter(room)}
                    </div>
                </div>;
    }

    render(): JSX.Element {
        let exits = this.props.room.exits;
        let north = (exits.north) ? this._buildDirection('North', '? ? ?') : null;
        let east = (exits.east) ? this._buildDirection('East', '? ? ?') : null;
        let south = (exits.south) ? this._buildDirection('South', '? ? ?') : null;
        let west = (exits.west) ? this._buildDirection('West', '? ? ?') : null;
        let up = (exits.up) ? this._buildDirection('Up', '? ? ?') : null;
        let down = (exits.down) ? this._buildDirection('Down', '? ? ?') : null;

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