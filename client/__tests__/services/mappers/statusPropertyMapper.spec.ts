import StatusPropertyMapper from '../../../src/common/service/mappers/statusPropertyMapper';
import StatusPropertyModel from '../../../src/common/service/models/statusPropertyModel';
import dto from '../../stubs/dtos/statusPropertyDto';

describe('service >> mappers >> StatusPropertyMapper', () => {

    describe('if we create a new instance', () => {
        const mapper = new StatusPropertyMapper();
        const model = mapper.map(dto);

        it('should map a dto into a model', () => {
            expect(model).toBeDefined();
            expect(model).toBeInstanceOf(StatusPropertyModel);
        });

        it('should have the right information', () => {
            expect(model.current).toBe(20);
            expect(model.max).toBe(50);
            expect(model.hide).toBe(false);
        });
    });
});