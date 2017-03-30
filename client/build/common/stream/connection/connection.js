"use strict";
var Rx = require('rx');
var connectionStatus_1 = require('../models/connectionStatus');
var appLogger_1 = require('../../service/models/appLogger');
var Connection = (function () {
    function Connection(url, onOpen) {
        var _this = this;
        this._url = url;
        this._websocket = new WebSocket(this._url);
        this._observable = Rx.Observable.create(function (observer) {
            _this._websocket.onmessage = function (event) { return _this._onMessage(event, observer); };
            _this._websocket.onclose = function (event) { return _this._onClose(event, observer); };
            _this._websocket.onerror = function (event) { return _this._onError(event, observer); };
        });
        this._websocket.onopen = function (event) { return _this._connect(event, onOpen); };
    }
    Object.defineProperty(Connection.prototype, "url", {
        get: function () {
            return this._url;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(Connection.prototype, "status", {
        get: function () {
            if (this._websocket.readyState === WebSocket.CONNECTING) {
                return connectionStatus_1.default.Connecting;
            }
            else if (this._websocket.readyState === WebSocket.OPEN) {
                return connectionStatus_1.default.Connected;
            }
            else {
                return connectionStatus_1.default.Disconnected;
            }
        },
        enumerable: true,
        configurable: true
    });
    Connection.prototype.getStream = function () {
        return this._observable;
    };
    Connection.prototype._connect = function (event, onOpen) {
        appLogger_1.log("Connected to " + this._url + " with websocket", event);
        if (onOpen) {
            onOpen();
        }
    };
    Connection.prototype._onMessage = function (event, observer) {
        appLogger_1.log("New message from " + this._url + " with data", event.data);
        observer.onNext(JSON.parse(event.data));
    };
    Connection.prototype._onError = function (event, observer) {
        appLogger_1.log("ERROR! on websocket from " + this._url + " with data", event.data);
        observer.onError(event.data);
    };
    Connection.prototype._onClose = function (event, observer) {
        appLogger_1.log("Disconnected from " + this._url + " with websocket", event);
        observer.onCompleted();
    };
    Connection.prototype.disconnect = function () {
        if (this._websocket !== null) {
            this._websocket.close();
            this._websocket = null;
        }
    };
    Connection.prototype.sendMessage = function (message) {
        if (this._websocket !== null) {
            this._websocket.send(JSON.stringify(message));
        }
        else {
            appLogger_1.log("Websocket disconnected, can't send any message", this._websocket);
        }
    };
    return Connection;
}());
Object.defineProperty(exports, "__esModule", { value: true });
exports.default = Connection;
//# sourceMappingURL=connection.js.map