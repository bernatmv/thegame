import MapService from './mapService';
import RoomDto from './dtos/roomDto';
import RoomModel from './models/roomModel';
import RoomMapper from './mappers/roomMapper';
import ProfileMapper from './mappers/profileMapper';
import StatusPropertyMapper from './mappers/statusPropertyMapper';
import PlayerMapper from './mappers/playerMapper';
import EnemyMapper from './mappers/enemyMapper';
import NPCMapper from './mappers/npcMapper';
import ItemMapper from './mappers/itemMapper';
import { debug } from './models/appLogger';

export default class MapServiceImpl implements MapService {
    private _roomMapper: RoomMapper;

    constructor() {
        let profileMapper = new ProfileMapper(new StatusPropertyMapper());
        this._roomMapper = new RoomMapper(
            new PlayerMapper(profileMapper),
            new EnemyMapper(profileMapper),
            new NPCMapper(profileMapper),
            new ItemMapper()
        );
    }

    // THIS ARE USED FOR PROCESSING SERVER MESSAGES

    loadRoom(dto: RoomDto): RoomModel {
        return this._roomMapper.map(dto);
    }
}