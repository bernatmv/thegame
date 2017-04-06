import EnemyModel from './enemyModel';
import NPCModel from './npcModel';
import PlayerModel from './playerModel';
import ItemModel from './itemModel';
import { debug } from './appLogger';

export default class RoomModel {
    constructor(
        public id: string,
        public title: string,
        public shortDescription: string,
        public description: string,
        public exits: {
            north: string,
            roomNorth?: RoomModel,
            east: string,
            roomEast?: RoomModel,
            south: string,
            roomSouth?: RoomModel,
            west: string,
            roomWest?: RoomModel,
            up: string,
            roomUp?: RoomModel,
            down: string,
            roomDown?: RoomModel
        },
        public players: Array<PlayerModel>,
        public enemies: Array<EnemyModel>,
        public npc: Array<NPCModel>,
        public items: Array<ItemModel>
    ) {
        //debug(`New room created`, this);
    }
}