import * as React from 'react';
import EnemyModel from '../../../../common/service/models/enemyModel';
import ItemModel from '../../../../common/service/models/itemModel';
import NPCModel from '../../../../common/service/models/npcModel';
import PlayerModel from '../../../../common/service/models/playerModel';
import RoomModel from '../../../../common/service/models/roomModel';
import RoomFooterProps from './roomFooterProps';
import * as style from './roomFooter.css';

export default class RoomFooter extends React.Component<RoomFooterProps, {}> {
    _buildRoomFooter(room: RoomModel): JSX.Element {
        let players:JSX.Element = null;
        let enemies:JSX.Element = null;
        let npcs:JSX.Element = null;
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
            npcs = <div className={style.room__footer__positive}>
                        {room.npc.map((npc: NPCModel, index: number) => <div key={'NPC_' + index}>
                                                            <span className={style.room__footer__npc}>{npc.id}</span> {'está aquí'}
                                                        </div>)}
                    </div>;
        }
        if (room.players.length > 0) {
            players = <div className={style.room__footer__positive}>
                        {room.players.map((player: PlayerModel, index: number) => <div key={'PLAYER_' + index}>
                                                            <span className={style.room__footer__player}>{player.id}</span> {'está aquí'}
                                                        </div>)}
                    </div>;
        }
        return <div>
                    {players}
                    {npcs}
                    {enemies}
                    {items}
                </div>;
    }

    render(): JSX.Element {
        return <div className={style.room__footer}>
                    {this._buildRoomFooter(this.props.room)}
                </div>;
    }
}