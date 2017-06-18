import StatusPropertyMapper from '../../../src/common/service/mappers/statusPropertyMapper';
import ProfileMapper from '../../../src/common/service/mappers/profileMapper';
import PlayerMapper from '../../../src/common/service/mappers/playerMapper';
import PlayerModel from '../../../src/common/service/models/playerModel';
import dto from '../../stubs/dtos/playerDto';

describe('service >> mappers >> PlayerMapper', () => {

    describe('if we create a new instance', () => {
        const mapper = new PlayerMapper(new ProfileMapper(new StatusPropertyMapper()));
        const model = mapper.map(dto);

        it('should map a dto into a model', () => {
            expect(model).toBeDefined();
            expect(model).toBeInstanceOf(PlayerModel);
        });

        it('should have the right information', () => {
            expect(model.id).toBeDefined();
            expect(model.profile).toBeDefined();
            expect(model.id).toBe('player-001');
        });
    });
});