import StatusPropertyMapper from '../../../src/common/service/mappers/statusPropertyMapper';
import ProfileMapper from '../../../src/common/service/mappers/profileMapper';
import NPCMapper from '../../../src/common/service/mappers/npcMapper';
import NPCModel from '../../../src/common/service/models/npcModel';
import dto from '../../stubs/dtos/npcDto';

describe('service >> mappers >> NPCMapper', () => {

    describe('if we create a new instance', () => {
        const mapper = new NPCMapper(new ProfileMapper(new StatusPropertyMapper()));
        const model = mapper.map(dto);

        it('should map a dto into a model', () => {
            expect(model).toBeDefined();
            expect(model).toBeInstanceOf(NPCModel);
        });

        it('should have the right information', () => {
            expect(model.id).toBeDefined();
            expect(model.profile).toBeDefined();
            expect(model.chatter).toBeDefined();
            expect(model.id).toBe('dummy-001');
        });
    });
});