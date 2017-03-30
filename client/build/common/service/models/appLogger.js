"use strict";
var LogLevel;
(function (LogLevel) {
    LogLevel[LogLevel["LOG"] = 1] = "LOG";
    LogLevel[LogLevel["DEBUG"] = 2] = "DEBUG";
})(LogLevel || (LogLevel = {}));
function _print(logLevel) {
    var payload = [];
    for (var _i = 1; _i < arguments.length; _i++) {
        payload[_i - 1] = arguments[_i];
    }
    if (logLevel === LogLevel.LOG) {
        console.log.apply(console, [logLevel].concat(payload));
    }
    else if (logLevel === LogLevel.DEBUG) {
        console.debug.apply(console, [logLevel].concat(payload));
    }
}
function log() {
    var payload = [];
    for (var _i = 0; _i < arguments.length; _i++) {
        payload[_i - 0] = arguments[_i];
    }
    _print.apply(void 0, [LogLevel.LOG].concat(payload));
}
exports.log = log;
function debug() {
    var payload = [];
    for (var _i = 0; _i < arguments.length; _i++) {
        payload[_i - 0] = arguments[_i];
    }
    _print.apply(void 0, [LogLevel.DEBUG].concat(payload));
}
exports.debug = debug;
//# sourceMappingURL=appLogger.js.map