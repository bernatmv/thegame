import PlayerModel from '../../src/common/service/models/playerModel';
import ProfileModel from '../../src/common/service/models/profileModel';
import StatusPropertyModel from '../../src/common/service/models/statusPropertyModel';

export const playerStubFactory = (id) => {
    return new PlayerModel(
        id,
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
};

export default playerStubFactory('TestUser');