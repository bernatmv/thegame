import EnemyModel from './enemyModel';
import NPCModel from './npcModel';
import ItemModel from './itemModel';

interface RoomModel {
    id: string;
    title: string;
    shortDescription: string;
    description: string;
    exits: {
        north: string;
        east: string;
        south: string;
        west: string;
        up: string;
        down: string;
    },
    enemies: Array<EnemyModel>;
    npc: Array<NPCModel>;
    items: Array<ItemModel>;
}
export default RoomModel;