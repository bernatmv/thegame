import { createAction } from 'redux-actions';
import ActionsConstants from '../../common/constants/actionsConstants';

export const loadRoom = createAction<string>(ActionsConstants.LoadRoom);