import { createAction } from 'redux-actions';
import ActionsConstants from '../../common/constants/actionsConstants';
import EnterRoomModel from '../reducers/models/enterRoomModel';
import LeaveRoomModel from '../reducers/models/leaveRoomModel';

export const loadRoom = createAction<string>(ActionsConstants.LoadRoom);
export const playerEntersRoom = createAction<EnterRoomModel>(ActionsConstants.PlayerEntersRoom);
export const playerLeavesRoom = createAction<LeaveRoomModel>(ActionsConstants.PlayerLeavesRoom);