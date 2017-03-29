export default class EnumBase {
    private _key: number;
    private _label: string;

    constructor(key: number, label: string) {
        this._key = key;
        this._label = label;
        let enumTypeMetadata = _getMetaData(<any>this.constructor);
        enumTypeMetadata[key] = this;
        enumTypeMetadata.values.push(this);
    }

    public get key(): number {
        return this._key;
    }

    public get label(): string {
        return this._label;
    }

    public static getValues<T extends EnumBase>(): T[] {
        return <T[]>(<any>this)._enumTypeMetadata.values.slice();
    }

    public toString(): string {
        return this._label;
    }
}

function _getMetaData(constructor: any) {
    if (!constructor._enumTypeMetadata) {
        constructor._enumTypeMetadata = {
            values: []
        };
    }
    return constructor._enumTypeMetadata;
}