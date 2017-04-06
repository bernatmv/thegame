import EnemyModel from '../../src/common/service/models/enemyModel';
import Goblin001 from './goblin_001';

export default class EnemiesRegistry {
    private static _instance: EnemiesRegistry;
    public map: Map<string, EnemyModel>;

    constructor() {
        this.map = new Map<string, EnemyModel>();
        this.map.set(Goblin001.id, Goblin001);
    }

    public static get Instance(): EnemiesRegistry {
        if (!EnemiesRegistry._instance) {
            EnemiesRegistry._instance = new EnemiesRegistry();
        }
        return EnemiesRegistry._instance;
    }
}