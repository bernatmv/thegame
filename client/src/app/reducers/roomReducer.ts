import { handleActions } from 'redux-actions';
import { debug } from '../../common/service/models/appLogger';
import ActionsConstants from '../../common/constants/actionsConstants';
import RoomModel from '../../common/service/models/roomModel';
import MapServiceImpl from '../middleware/services/mapServiceImpl';
import initialState from './state/initialState';

let _mapService:MapServiceImpl = new MapServiceImpl();

export default handleActions<RoomModel, RoomModel>({
    [ActionsConstants.LoadRoom]: (state: RoomModel, action: ReduxActions.Action<RoomModel>): RoomModel => {
        return action.payload;
    },
    [ActionsConstants.Move]: (state: RoomModel, action: ReduxActions.Action<string>): RoomModel => {
        return _mapService.moveFrom(state, action.payload);
    }
}, initialState.room);