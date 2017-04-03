import { handleActions } from 'redux-actions';
import RootState from './state/rootState';
import ActionsConstants from '../../common/constants/actionsConstants';
import RoomModel from '../../common/service/models/roomModel';
import initialState from './state/initialState';

export default handleActions<RootState, RoomModel>({
    [ActionsConstants.LoadRoom]: (state: RootState, action: ReduxActions.Action<RoomModel>): RootState => {
        return Object.assign({}, state, { room: action.payload });
    }
}, initialState);