"use strict";
var React = require('react');
var ReactDOM = require('react-dom');
var react_redux_1 = require('react-redux');
var react_router_1 = require('react-router');
var history_1 = require('history');
var chatStore_1 = require('./app/stores/chatStore');
var TheGame_1 = require('./app/containers/TheGame');
var chatStore = chatStore_1.configureChatStore();
var history = history_1.createBrowserHistory();
ReactDOM.render(React.createElement(react_redux_1.Provider, {store: chatStore}, 
    React.createElement(react_router_1.Router, {history: history}, 
        React.createElement(react_router_1.Switch, null, 
            React.createElement(react_router_1.Route, {path: '/', component: TheGame_1.TheGame})
        )
    )
), document.getElementById('root'));
//# sourceMappingURL=index.js.map