import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { Router, Route, Switch } from 'react-router';
import createHistory from 'history/createBrowserHistory';
import { configureChatStore } from './app/stores/chatStore';
import { TheGame } from './app/containers/theGame/theGameContainer';

const chatStore = configureChatStore();
const history = createHistory();

ReactDOM.render(
  <Provider store={chatStore}>
    <Router history={history}>
      <Switch>
        <Route path='/' component={TheGame} />
      </Switch>
    </Router>
  </Provider>,
  document.getElementById('root')
);