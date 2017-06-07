import EnemyModel from '../../src/common/service/models/enemyModel';
import ProfileModel from '../../src/common/service/models/profileModel';
import StatusPropertyModel from '../../src/common/service/models/statusPropertyModel';

export const enemyStubFactory = (id) => {
    return new EnemyModel(
        id,
        new ProfileModel(
            'Enemy Name',
            'Goblin',
            'goblin',
            'goblins',
            'Warrior',
            1,
            new StatusPropertyModel(1000, 1000),
            null,
            null
        ),
        [
            'Vicavorausan or deaavh!',
            'Inavruderuk, drepa avhem!',
            'Proavecav avhe milambak!',
            'Nalkroro ayh lat doaumn avhiuk avo uuk?',
            'Senav avhem avo avheir goddeukuk'
            ]
    );
};

export default enemyStubFactory('TestEnemy');