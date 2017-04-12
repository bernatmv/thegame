import EnemyModel from '../service/models/enemyModel';
import StatusPropertyMapper from '../service/mappers/statusPropertyMapper';
import ProfileMapper from '../service/mappers/profileMapper';
import EnemyMapper from '../service/mappers/enemyMapper';

//TODO: CLEAN => Loading temporarily while we develop more the BE
import * as GoblinDto001 from '../../../../assets/enemies/goblin_001.json';

export default class EnemiesRegistry {
    private _enemyMapper: EnemyMapper;

    private static _instance: EnemiesRegistry;
    public map: Map<string, EnemyModel>;

    constructor() {
        //TODO: create a container for dependency injection
        this._enemyMapper = new EnemyMapper(new ProfileMapper(new StatusPropertyMapper()));

        this.map = new Map<string, EnemyModel>();
        this.map.set(GoblinDto001.id, this._enemyMapper.map(GoblinDto001));
    }

    public static get Instance(): EnemiesRegistry {
        if (!EnemiesRegistry._instance) {
            EnemiesRegistry._instance = new EnemiesRegistry();
        }
        return EnemiesRegistry._instance;
    }
}