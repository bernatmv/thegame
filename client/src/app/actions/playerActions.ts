import { createAction } from 'redux-actions';
import ActionsConstants from '../../common/constants/actionsConstants';

export const move = createAction<string>(ActionsConstants.Move);