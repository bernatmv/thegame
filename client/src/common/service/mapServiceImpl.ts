import MapService from './mapService';
import RoomDto from './dtos/roomDto';
import RoomModel from './models/roomModel';
import RoomsRegistry from '../helpers/roomsRegistry';
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

    // THIS ARE USED FOR LOCAL MOVEMENT AND TESTING

    getRoom(id: string): RoomModel {
        let room = RoomsRegistry.Instance.map.get(id);
        if (room.exits.north) {
            room.exits.roomNorth = RoomsRegistry.Instance.map.get(room.exits.north);
        }
        if (room.exits.east) {
            room.exits.roomEast = RoomsRegistry.Instance.map.get(room.exits.east);
        }
        if (room.exits.south) {
            room.exits.roomSouth = RoomsRegistry.Instance.map.get(room.exits.south);
        }
        if (room.exits.west) {
            room.exits.roomWest = RoomsRegistry.Instance.map.get(room.exits.west);
        }
        if (room.exits.up) {
            room.exits.roomUp = RoomsRegistry.Instance.map.get(room.exits.up);
        }
        if (room.exits.down) {
            room.exits.roomDown = RoomsRegistry.Instance.map.get(room.exits.down);
        }
        return room;
    }

    moveFrom(from: RoomModel, direction: string): RoomModel {
        if (from && from.exits && from.exits[direction]) {
            return this.getRoom(from.exits[direction]);
        } else {
            return from;
        }
    }    
}