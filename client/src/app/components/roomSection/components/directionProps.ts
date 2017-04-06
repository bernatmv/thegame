import RoomModel from '../../../../common/service/models/roomModel';

interface Direction {
    title: string;
    nextRoom?: RoomModel;
    move: () => any;
}
export default Direction;