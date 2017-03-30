"use strict";
var appLogger_1 = require('../../common/service/models/appLogger');
function loggerMiddleware(store) {
    return function (next) {
        return function (action) {
            appLogger_1.log(action);
            return next(action);
        };
    };
}
Object.defineProperty(exports, "__esModule", { value: true });
exports.default = loggerMiddleware;
//# sourceMappingURL=loggerMiddleware.js.map