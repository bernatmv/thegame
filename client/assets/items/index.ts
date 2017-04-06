import ItemModel from '../../src/common/service/models/itemModel';
import Chicken001 from './chicken_001';

export default class ItemsRegistry {
    private static _instance: ItemsRegistry;
    public map: Map<string, ItemModel>;

    constructor() {
        this.map = new Map<string, ItemModel>();
        this.map.set(Chicken001.id, Chicken001);
    }

    public static get Instance(): ItemsRegistry {
        if (!ItemsRegistry._instance) {
            ItemsRegistry._instance = new ItemsRegistry();
        }
        return ItemsRegistry._instance;
    }
}