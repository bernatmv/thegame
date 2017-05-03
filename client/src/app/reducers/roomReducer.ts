import { handleActions } from 'redux-actions';
import { debug } from '../../common/service/models/appLogger';
import ActionsConstants from '../../common/constants/actionsConstants';
import RoomModel from '../../common/service/models/roomModel';
import initialState from './state/initialState';
import EnterRoomModel from './models/enterRoomModel';
import LeaveRoomModel from './models/leaveRoomModel';

export default handleActions<RoomModel, RoomModel>({
    [ActionsConstants.LoadRoom]: (state: RoomModel, action: ReduxActions.Action<RoomModel>): RoomModel => {
        return action.payload;
    },/*
    [ActionsConstants.PlayerLeavesRoom]: (state: RoomModel, action: ReduxActions.Action<LeaveRoomModel>): RoomModel => {
        return _mapService.getRoom(action.payload);
    },
    [ActionsConstants.PlayerEntersRoom]: (state: RoomModel, action: ReduxActions.Action<EnterRoomModel>): RoomModel => {
        return _mapService.getRoom(action.payload);
    },*/
}, initialState.room);