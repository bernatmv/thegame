import ItemMapper from '../../../src/common/service/mappers/itemMapper';
import ItemModel from '../../../src/common/service/models/itemModel';
import dto from '../../stubs/dtos/itemDto';

describe('service >> mappers >> ItemMapper', () => {

    describe('if we create a new instance', () => {
        const mapper = new ItemMapper();
        const model = mapper.map(dto);

        it('should map a dto into a model', () => {
            expect(model).toBeDefined();
            expect(model).toBeInstanceOf(ItemModel);
        });

        it('should have the right information', () => {
            expect(model.id).toBeDefined();
            expect(model.id).toBe('chicken-001');
        });
    });
});