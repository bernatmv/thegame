import EnemyModel from './enemyModel';
import NPCModel from './npcModel';
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
            east: string,
            south: string,
            west: string,
            up: string,
            down: string
        },
        public enemies: Array<EnemyModel>,
        public npc: Array<NPCModel>,
        public items: Array<ItemModel>
    ) {
        debug(`New room created`, this);
    }
}