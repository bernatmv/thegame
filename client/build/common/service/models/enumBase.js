"use strict";
var EnumBase = (function () {
    function EnumBase(key, label) {
        this._key = key;
        this._label = label;
        var enumTypeMetadata = _getMetaData(this.constructor);
        enumTypeMetadata[key] = this;
        enumTypeMetadata.values.push(this);
    }
    Object.defineProperty(EnumBase.prototype, "key", {
        get: function () {
            return this._key;
        },
        enumerable: true,
        configurable: true
    });
    Object.defineProperty(EnumBase.prototype, "label", {
        get: function () {
            return this._label;
        },
        enumerable: true,
        configurable: true
    });
    EnumBase.getValues = function () {
        return this._enumTypeMetadata.values.slice();
    };
    EnumBase.prototype.toString = function () {
        return this._label;
    };
    return EnumBase;
}());
Object.defineProperty(exports, "__esModule", { value: true });
exports.default = EnumBase;
function _getMetaData(constructor) {
    if (!constructor._enumTypeMetadata) {
        constructor._enumTypeMetadata = {
            values: []
        };
    }
    return constructor._enumTypeMetadata;
}
//# sourceMappingURL=enumBase.js.map