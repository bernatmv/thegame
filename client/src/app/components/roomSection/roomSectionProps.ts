import RoomModel from '../../../common/service/models/roomModel';

interface RoomSectionProps {
    room: RoomModel;
    move: (direction: string) => any;
}
export default RoomSectionProps;