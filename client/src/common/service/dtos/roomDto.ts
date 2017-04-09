import PlayerDto from './playerDto';
import EnemyDto from './enemyDto';
import NPCDto from './npcDto';
import ItemDto from './itemDto';

interface RoomDto {
    id: string;
    title: string;
    shortDescription: string;
    description: string;
    exits: {
        north?: string;
        east?: string;
        south?: string;
        west?: string;
        up?: string;
        down?: string
    };
    players: Array<PlayerDto>;
    enemies: Array<EnemyDto>;
    npc: Array<NPCDto>;
    items: Array<ItemDto>;
}
export default RoomDto;