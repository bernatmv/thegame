import * as React from 'react';
import {Tooltip} from 'react-lightweight-tooltip';
import NPCModel from '../../../../common/service/models/npcModel';
import PlayerModel from '../../../../common/service/models/playerModel';
import RoomModel from '../../../../common/service/models/roomModel';
import RoomFooterProps from './roomFooterProps';
import * as style from './roomFooter.css';

interface LineBaseInfo {
    singular: string;
    plural: string;
    description?: string;
}

interface LinePrintInfo {
    count: number;
    label: string;
    description: string;
}

const tooltipStyle = {
    wrapper: {
        cursor: 'pointer',
        color: '#fff'
    },
    gap: {},
    content: {
        backgroundColor: '#161616',
        color: '#fff',
        cursor: 'pointer',
        fontSize: '12px'
    },
    tooltip: {
        backgroundColor: '#161616',
        borderRadius: '5px'
    },
    arrow: {
        borderTop: 'solid #161616 5px'
    }
};

export default class RoomFooter extends React.Component<RoomFooterProps, {}> {
    _buildFooterLine(preText: string, elements: LineBaseInfo[]): JSX.Element {
        let reducedEl = elements.reduce(
            (acc: {items: LinePrintInfo[], names: string[]}, curr: LineBaseInfo) => {
                if (acc.items[curr.singular]) {
                    acc.items[curr.singular] = {
                        count: acc.items[curr.singular].count + 1,
                        label: curr.plural 
                    };
                } else {
                    acc.items[curr.singular] = {
                        count: 1,
                        label: curr.singular,
                        description: curr.description ? curr.description : null
                    };
                    acc.names.push(curr.singular);
                }
                return acc;
            }, {items: [], names: []});
        return <div>
                    {preText} 
                    {reducedEl.names
                        .map(name => reducedEl.items[name])
                        .map((item: LinePrintInfo, i) => {
                            if (item.description) {
                                return <Tooltip content={item.description} styles={tooltipStyle} key={i}> {item.count} {item.label}</Tooltip>;
                            } else {
                                return <span key={i}> {item.count} {item.label}</span>;
                            }
                        })
                    }
                </div>;
    }

    _buildRoomFooter(room: RoomModel): JSX.Element {
        let players:JSX.Element = null;
        let enemies:JSX.Element = null;
        let npcs:JSX.Element = null;
        let items:JSX.Element = null;
        if (room.items.length === 0) {
            items = <div>{'No hay objetos cerca'}</div>;
        } else {
            items = this._buildFooterLine('Hay ', room.items);
        }
        if (room.enemies.length === 0) {
            enemies = <div>{'No ves enemigos'}</div>;
        } else {
            enemies = this._buildFooterLine('Ves ', room.enemies.map(enemy => enemy.profile));
        }
        if (room.npc.length > 0) {
            npcs = <div>
                        {room.npc.map((npc: NPCModel, index: number) => <div key={'NPC_' + index}>
                                                            <span className={style.room__footer__npc}>{npc.id}</span> {'está aquí'}
                                                        </div>)}
                    </div>;
        }
        if (room.players.length > 0) {
            players = <div>
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