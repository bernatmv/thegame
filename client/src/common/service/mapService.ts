import RoomDto from './dtos/roomDto';
import RoomModel from './models/roomModel';

interface MapService {
    loadRoom(dto: RoomDto): RoomModel;
}
export default MapService;