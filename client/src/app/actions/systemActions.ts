import { createAction } from 'redux-actions';
import ActionsConstants from '../../common/constants/actionsConstants';
import RoomModel from '../../common/service/models/roomModel';
import EnterRoomModel from '../reducers/models/enterRoomModel';
import LeaveRoomModel from '../reducers/models/leaveRoomModel';

export const loadRoom = createAction<RoomModel>(ActionsConstants.LoadRoom);
export const playerEntersRoom = createAction<EnterRoomModel>(ActionsConstants.PlayerEntersRoom);
export const playerLeavesRoom = createAction<LeaveRoomModel>(ActionsConstants.PlayerLeavesRoom);