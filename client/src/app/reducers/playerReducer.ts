import { handleActions } from 'redux-actions';
import { debug } from '../../common/service/models/appLogger';
import ActionsConstants from '../../common/constants/actionsConstants';
import PlayerModel from '../../common/service/models/playerModel';
import AuthServiceImpl from '../../common/service/authServiceImpl';
import initialState from './state/initialState';

let _authService = new AuthServiceImpl();

export default handleActions<PlayerModel, PlayerModel>({
    [ActionsConstants.Login]: (state: PlayerModel, action: ReduxActions.Action<string>): PlayerModel => {
        return _authService.login(action.payload);
    }
}, initialState.player);