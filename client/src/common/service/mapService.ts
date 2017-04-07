import RoomModel from './models/roomModel';

interface MapService {
    getRoom(id: string): RoomModel;
    moveFrom(from: RoomModel, direction: 'north' | 'south' | 'east' | 'west' | 'up' | 'down'): RoomModel;
}
export default MapService;