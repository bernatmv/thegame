import {nullable} from '../../src/common/helpers/functions';
import playerStub from '../stubs/playerStub';

describe('helper >> functions', () => {

    describe('if we call nullable', () => {
        it('should return value on a primitive', () => {
            expect(nullable(0)).toBe(0);
            expect(nullable(0)).not.toBe(null);
            expect(nullable(false)).toBe(false);
            expect(nullable(false)).not.toBe(null);
            expect(nullable('')).toBe('');
            expect(nullable('')).not.toBe(null);
            let arr = [1,2,3];
            expect(nullable(arr)).toBe(arr);
            expect(nullable(arr)).not.toBe(null);
        });

        it('should return value on an object instance', () => {
            let obj = {test: 'object'};
            expect(nullable(obj)).toBe(obj);
            expect(nullable(obj)).not.toBe(null);
            expect(nullable(obj.test)).toBe(obj.test);
            expect(nullable(obj.test)).not.toBe(null);
            expect(nullable(playerStub)).toBe(playerStub);
            expect(nullable(playerStub)).not.toBe(null);
        });

        it('should return null on undefined', () => {
            expect(nullable(undefined)).toBe(null);
            expect(nullable(null)).toBe(null);
            let obj;
            expect(nullable(obj)).toBe(null);
            obj = {test: 'object'};
            expect(nullable(obj.wrong)).toBe(null);
        });
    });
});