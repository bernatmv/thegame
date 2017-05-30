import { createAction } from 'redux-actions';
import ActionsConstants from '../../common/constants/actionsConstants';

export const login = createAction<string>(ActionsConstants.Login);
export const signup = createAction<void>(ActionsConstants.SignUp);