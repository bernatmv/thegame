import { createAction } from 'redux-actions';
import ActionsConstants from '../../common/constants/actionsConstants';
import RoomModel from '../../common/service/models/roomModel';

export const loadRoom = createAction<RoomModel>(ActionsConstants.LoadRoom);