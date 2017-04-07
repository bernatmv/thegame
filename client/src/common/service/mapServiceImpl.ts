import MapService from './mapService';
import RoomModel from './models/roomModel';
import RoomsRegistry from '../helpers/roomsRegistry';
import { debug } from './models/appLogger';

export default class MapServiceImpl implements MapService {
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