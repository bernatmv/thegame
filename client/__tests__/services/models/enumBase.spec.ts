import EnumBase from '../../../src/common/service/models/enumBase';

describe('service >> models >> EnumBase', () => {

    describe('if we create a new instance', () => {
        const test1 = new EnumBase(1, 'Test1');
        const test2 = new EnumBase(2, 'Test2');

        it('should have a key', () => {
            expect(test1.key).toBeDefined();
            expect(test1.key).toBe(1);
        });

        it('should have a label', () => {
            expect(test1.label).toBeDefined();
            expect(test1.label).toBe('Test1');
        });

        it('should have a toString conversion', () => {
            expect(test1.toString).toBeDefined();
            expect(test1.toString()).toBe('Test1');
        });

        it('should hold all values', () => {
            expect(EnumBase.getValues).toBeDefined();
            expect(EnumBase.getValues()).toHaveLength(2);
        });
    });
});