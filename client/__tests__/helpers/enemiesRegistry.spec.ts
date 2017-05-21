import EnemiesRegistry from '../../src/common/helpers/enemiesRegistry';
import EnemyModel from '../../src/common/service/models/enemyModel';
import * as GoblinDto001 from '../../../assets/enemies/goblin_001.json';

describe('helper >> enemies registry', () => {
    it('should be a singleton', () => {
        expect(EnemiesRegistry.Instance).toBeDefined();
        expect(EnemiesRegistry.Instance).toBeInstanceOf(EnemiesRegistry);
    });

    it('map should not be empty', () => {
        expect(EnemiesRegistry.Instance.map).toBeDefined();
        expect(EnemiesRegistry.Instance.map.size).toBeGreaterThan(0);
    });

    it('map should hold an instance of goblin', () => {
        expect(EnemiesRegistry.Instance.map.get(GoblinDto001.id)).toBeDefined();
    });

    it('map should hold instances of EnemyModel', () => {
        expect(EnemiesRegistry.Instance.map.get(GoblinDto001.id)).toBeInstanceOf(EnemyModel);
    });
});