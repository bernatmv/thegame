import MapService from './mapService';
import RoomModel from '../../../common/service/models/roomModel';
import RoomsRegistry from '../../../../assets/rooms/';
import { debug } from '../../../common/service/models/appLogger';

export default class MapServiceImpl implements MapService {
    getRoom(id: string): RoomModel {
        return RoomsRegistry.Instance.map.get(id);
    }

    moveFrom(from: RoomModel, direction: 'north' | 'south' | 'east' | 'west' | 'up' | 'down'): RoomModel {
        if (from && from.exits) {
            return RoomsRegistry.Instance.map.get(from.exits[direction]);
        }
    }    
}