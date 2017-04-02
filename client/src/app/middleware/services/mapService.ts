import RoomModel from '../../../common/service/models/roomModel';

interface MapService {
    loadWorld(firstRoom: RoomModel): void;
    getRoom(id: string): RoomModel;
    moveTo(from: string, direction: 'north' | 'south' | 'east' | 'west' | 'up' | 'down'): RoomModel;
}
export default MapService;