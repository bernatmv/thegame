import ItemMapper from '../service/mappers/itemMapper';
import ItemModel from '../service/models/itemModel';

//TODO: CLEAN => Loading temporarily while we develop more the BE
import * as ChickenDto001 from '../../../../assets/items/chicken_001.json';

export default class ItemsRegistry {
    private _itemMapper: ItemMapper;

    private static _instance: ItemsRegistry;
    public map: Map<string, ItemModel>;

    constructor() {
        //TODO: create a container for dependency injection
        this._itemMapper = new ItemMapper();

        this.map = new Map<string, ItemModel>();
        this.map.set(ChickenDto001.id, this._itemMapper.map(ChickenDto001));
    }

    public static get Instance(): ItemsRegistry {
        if (!ItemsRegistry._instance) {
            ItemsRegistry._instance = new ItemsRegistry();
        }
        return ItemsRegistry._instance;
    }
}