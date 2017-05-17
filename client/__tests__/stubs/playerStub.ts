import PlayerModel from '../../src/common/service/models/playerModel';
import ProfileModel from '../../src/common/service/models/profileModel';
import StatusPropertyModel from '../../src/common/service/models/statusPropertyModel';

export default new PlayerModel(
    'TestUser',
    new ProfileModel(
        'Test User Name',
        'Robot',
        'robot',
        'robots',
        'Tester',
        1,
        new StatusPropertyModel(1000, 1000),
        new StatusPropertyModel(1000, 1000),
        new StatusPropertyModel(1000, 1000)
    )
);