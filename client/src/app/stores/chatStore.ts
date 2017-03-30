import { createStore, applyMiddleware, Store } from 'redux';
import logger from '../middleware/loggerMiddleware';
import websocket from '../middleware/websocketMiddleware';
import rootReducer from '../reducers/chatReducer';
import RootState from '../reducers/state/rootState';

export function configureChatStore(initialState?: RootState): Store<RootState> {
  // dev tools
  const create = window.devToolsExtension
    ? window.devToolsExtension()(createStore)
    : createStore;

  // applyMiddleware
  const createStoreWithMiddleware = applyMiddleware(logger, websocket)(create);

  // that's the only required step for the app logic
  const store = createStoreWithMiddleware(rootReducer, initialState) as Store<RootState>;

  // hot reload to save app state
  if (module.hot) {
    module.hot.accept('../reducers/chatReducer', () => {
      const nextReducer = require('../reducers/chatReducer');
      store.replaceReducer(nextReducer);
    });
  }

  return store;
}