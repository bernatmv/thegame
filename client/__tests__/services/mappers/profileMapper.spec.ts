import StatusPropertyMapper from '../../../src/common/service/mappers/statusPropertyMapper';
import ProfileMapper from '../../../src/common/service/mappers/profileMapper';
import ProfileModel from '../../../src/common/service/models/profileModel';
import dto from '../../stubs/dtos/profileDto';

describe('service >> mappers >> ProfileMapper', () => {

    describe('if we create a new instance', () => {
        const mapper = new ProfileMapper(new StatusPropertyMapper());
        const model = mapper.map(dto);

        it('should map a dto into a model', () => {
            expect(model).toBeDefined();
            expect(model).toBeInstanceOf(ProfileModel);
        });

        it('should have the right information', () => {
            expect(model.name).toBe(null);
            expect(model.singular).toBe('human');
            expect(model.plural).toBe('humans');
            expect(model.level).toBe(3);
            expect(model.hp).toBeDefined();
        });
    });
});