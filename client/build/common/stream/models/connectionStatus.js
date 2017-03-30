"use strict";
var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
var enumBase_1 = require('../../service/models/enumBase');
var ConnectionStatus = (function (_super) {
    __extends(ConnectionStatus, _super);
    function ConnectionStatus() {
        _super.apply(this, arguments);
    }
    ConnectionStatus.Disconnected = new ConnectionStatus(1, 'Disconnected');
    ConnectionStatus.Connecting = new ConnectionStatus(2, 'Connecting');
    ConnectionStatus.Connected = new ConnectionStatus(3, 'Connected');
    return ConnectionStatus;
}(enumBase_1.default));
Object.defineProperty(exports, "__esModule", { value: true });
exports.default = ConnectionStatus;
//# sourceMappingURL=connectionStatus.js.map