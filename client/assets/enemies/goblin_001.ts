import ProfileModel from '../../src/common/service/models/profileModel';
import EnemyModel from '../../src/common/service/models/enemyModel';
import RaceConstants from '../../src/common/constants/raceConstants';

export default new EnemyModel(
    'goblin-001',
    new ProfileModel(
        null,
        RaceConstants.Goblin,
        null,
        1,
        20,
        5,
        25
    ),
    [
        'Vicavorausan or deaavh!',
        'Inavruderuk, drepa avhem!',
        'Proavecav avhe milambak!',
        'Nalkroro ayh lat doaumn avhiuk avo uuk?',
        'Senav avhem avo avheir goddeukuk'
    ]
);