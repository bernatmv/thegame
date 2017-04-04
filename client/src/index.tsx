import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { Router, Route, Switch } from 'react-router';
import createHistory from 'history/createBrowserHistory';
import { configureStore } from './app/stores/theGameStore';
import { TheGame } from './app/containers/theGame/theGameContainer';
import initialState from './app/reducers/state/initialState';
import consoleFun from './config/consoleFun';

const store = configureStore(initialState);
const history = createHistory();

ReactDOM.render(
  <Provider store={store}>
    <Router history={history}>
      <Switch>
        <Route path='/' component={TheGame} />
      </Switch>
    </Router>
  </Provider>,
  document.getElementById('root')
);

consoleFun();