import { handleActions } from 'redux-actions';
import { debug } from '../../common/service/models/appLogger';
import ActionsConstants from '../../common/constants/actionsConstants';
import RoomModel from '../../common/service/models/roomModel';
import MapServiceImpl from '../../common/service/mapServiceImpl';
import initialState from './state/initialState';
import EnterRoomModel from './models/enterRoomModel';
import LeaveRoomModel from './models/leaveRoomModel';

let _mapService:MapServiceImpl = new MapServiceImpl();

export default handleActions<RoomModel, RoomModel>({
    [ActionsConstants.LoadRoom]: (state: RoomModel, action: ReduxActions.Action<string>): RoomModel => {
        return _mapService.getRoom(action.payload);
    },
    [ActionsConstants.PlayerLeavesRoom]: (state: RoomModel, action: ReduxActions.Action<LeaveRoomModel>): RoomModel => {
        return _mapService.getRoom(action.payload);
    },
    [ActionsConstants.PlayerEntersRoom]: (state: RoomModel, action: ReduxActions.Action<EnterRoomModel>): RoomModel => {
        return _mapService.getRoom(action.payload);
    },
    [ActionsConstants.Move]: (state: RoomModel, action: ReduxActions.Action<string>): RoomModel => {
        return _mapService.moveFrom(state, action.payload);
    }
}, initialState.room);