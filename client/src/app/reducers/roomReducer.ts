import { handleActions } from 'redux-actions';
import { debug } from '../../common/service/models/appLogger';
import ActionsConstants from '../../common/constants/actionsConstants';
import RoomModel from '../../common/service/models/roomModel';
import initialState from './state/initialState';
import EnterRoomModel from './models/enterRoomModel';
import LeaveRoomModel from './models/leaveRoomModel';

export default handleActions<RoomModel, RoomModel | LeaveRoomModel | EnterRoomModel | string>({
    [ActionsConstants.LoadRoom]: (state: RoomModel, action: ReduxActions.Action<RoomModel>): RoomModel => {
        return action.payload;
    },
    [ActionsConstants.PlayerLeavesRoom]: (state: RoomModel, action: ReduxActions.Action<LeaveRoomModel>): RoomModel => {
        return Object.assign({}, state, { players: state.players.filter(p => p.id !== action.payload.user.id) });
    },
    [ActionsConstants.PlayerEntersRoom]: (state: RoomModel, action: ReduxActions.Action<EnterRoomModel>): RoomModel => {
        if (state.players.find(p => p.id === action.payload.user.id)) {
            return state;
        }
        return Object.assign({}, state, { players: [...state.players, action.payload.user] });
    },
    [ActionsConstants.Move]: (state: RoomModel, action: ReduxActions.Action<string>): RoomModel => {
        return state;
    }
}, initialState.room);