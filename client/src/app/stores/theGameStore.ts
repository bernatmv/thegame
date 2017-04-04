import { createStore, applyMiddleware, Store, combineReducers } from 'redux';
import logger from '../middleware/loggerMiddleware';
import websocket from '../middleware/websocketMiddleware';
import connectionReducer from '../reducers/connectionReducer';
import chatReducer from '../reducers/chatReducer';
import roomReducer from '../reducers/roomReducer';
import RootState from '../reducers/state/rootState';

export function configureStore(initialState?: RootState): Store<RootState> {
  // dev tools
  let create = window.devToolsExtension
    ? window.devToolsExtension()(createStore)
    : createStore;
  let reducers = {
    chats: chatReducer,
    connection: connectionReducer,
    room: roomReducer
  };

  // applyMiddleware
  const createStoreWithMiddleware = applyMiddleware(logger, websocket)(create);

  // that's the only required step for the app logic
  const store = createStoreWithMiddleware(combineReducers(reducers), initialState) as Store<RootState>;

  // hot reload to save app state
  if (module.hot) {
    module.hot.accept('../reducers/chatReducer', () => {
      const nextReducer = require('../reducers/chatReducer');
      store.replaceReducer(nextReducer);
    });
  }

  return store;
}