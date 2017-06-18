import StatusPropertyMapper from '../../../src/common/service/mappers/statusPropertyMapper';
import ProfileMapper from '../../../src/common/service/mappers/profileMapper';
import EnemyMapper from '../../../src/common/service/mappers/enemyMapper';
import EnemyModel from '../../../src/common/service/models/enemyModel';
import dto from '../../stubs/dtos/enemyDto';

describe('service >> mappers >> EnemyMapper', () => {

    describe('if we create a new instance', () => {
        const mapper = new EnemyMapper(new ProfileMapper(new StatusPropertyMapper()));
        const model = mapper.map(dto);

        it('should map a dto into a model', () => {
            expect(model).toBeDefined();
            expect(model).toBeInstanceOf(EnemyModel);
        });

        it('should have the right information', () => {
            expect(model.id).toBeDefined();
            expect(model.profile).toBeDefined();
            expect(model.chatter).toBeDefined();
            expect(model.id).toBe('goblin-001');
        });
    });
});