"use strict";
var redux_1 = require('redux');
var logger_1 = require('../middleware/logger');
var chatReducer_1 = require('../reducers/chatReducer');
function configureChatStore(initialState) {
    var create = window.devToolsExtension
        ? window.devToolsExtension()(redux_1.createStore)
        : redux_1.createStore;
    var createStoreWithMiddleware = redux_1.applyMiddleware(logger_1.default)(create);
    var store = createStoreWithMiddleware(chatReducer_1.default, initialState);
    if (module.hot) {
        module.hot.accept('../reducers', function () {
            var nextReducer = require('../reducers');
            store.replaceReducer(nextReducer);
        });
    }
    return store;
}
exports.configureChatStore = configureChatStore;
//# sourceMappingURL=chatStore.js.map