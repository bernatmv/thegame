"use strict";
var redux_actions_1 = require('redux-actions');
var systemConstants_1 = require('../../common/constants/systemConstants');
var actionsConstants_1 = require('../../common/constants/actionsConstants');
var initialState = {
    chats: [{
            sender: systemConstants_1.default.SystemUser,
            message: '',
            received: new Date()
        }]
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.default = redux_actions_1.handleActions((_a = {},
    _a[actionsConstants_1.default.ReceiveChat] = function (state, action) {
        state.chats.push(action.payload);
        return state;
    },
    _a[actionsConstants_1.default.SendChat] = function (state, action) { return state; },
    _a
), initialState);
var _a;
//# sourceMappingURL=chatReducer.js.map