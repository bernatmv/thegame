import { handleActions } from 'redux-actions';
import { debug } from '../../common/service/models/appLogger';
import ActionsConstants from '../../common/constants/actionsConstants';
import SystemModel from '../../common/service/models/systemModel';
import initialState from './state/initialState';

export default handleActions<SystemModel, SystemModel>({
    [ActionsConstants.EndIntro]: (state: SystemModel, action: ReduxActions.Action<void>): SystemModel => {
        return Object.assign({}, state, { playedIntro: true });
    }
}, initialState.system);