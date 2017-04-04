import { handleActions } from 'redux-actions';
import { debug } from '../../common/service/models/appLogger';
import ActionsConstants from '../../common/constants/actionsConstants';
import RoomModel from '../../common/service/models/roomModel';
import initialState from './state/initialState';

export default handleActions<RoomModel, RoomModel>({
    [ActionsConstants.LoadRoom]: (state: RoomModel, action: ReduxActions.Action<RoomModel>): RoomModel => {
        return Object.assign({}, action.payload);
    }
}, initialState.room);