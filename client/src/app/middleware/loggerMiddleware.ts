import { log } from '../../common/service/models/appLogger';

export default function loggerMiddleware(store) {
  return next => 
            (action) => {
                log(`ACTION - `, action);
                return next(action);
            };
}