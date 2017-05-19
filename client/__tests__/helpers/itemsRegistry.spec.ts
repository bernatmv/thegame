import ItemsRegistry from '../../src/common/helpers/itemsRegistry';
import ItemModel from '../../src/common/service/models/itemModel';
import * as ChickenDto001 from '../../../assets/items/chicken_001.json';

describe('helper >> items registry', () => {
    it('should be a singleton', () => {
        expect(ItemsRegistry.Instance).toBeDefined();
        expect(ItemsRegistry.Instance).toBeInstanceOf(ItemsRegistry);
    });

    it('map should not be empty', () => {
        expect(ItemsRegistry.Instance.map).toBeDefined();
        expect(ItemsRegistry.Instance.map.size).toBeGreaterThan(0);
    });

    it('map should hold an instance of chicken', () => {
        expect(ItemsRegistry.Instance.map.get(ChickenDto001.id)).toBeDefined();
    });

    it('map should hold instances of ItemModel', () => {
        expect(ItemsRegistry.Instance.map.get(ChickenDto001.id)).toBeInstanceOf(ItemModel);
    });
});