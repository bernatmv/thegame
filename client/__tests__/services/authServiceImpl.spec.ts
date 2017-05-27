import AuthServiceImpl from '../../src/common/service/authServiceImpl';
import playerStub from '../stubs/playerStub';

describe('service >> AuthServiceImpl', () => {
    const authServiceImpl = new AuthServiceImpl();

    it('should return a user on login', () => {
        const id = 'TestUser - 100';
        const originalStub = Object.assign({}, playerStub);
        expect(authServiceImpl.login(id)).not.toEqual(originalStub);
        expect(authServiceImpl.login(id)).toEqual(Object.assign({}, originalStub, {id: id}));
    });

    it('should return a user on getUser', () => {
        const id = 'TestUser - 101';
        const originalStub = Object.assign({}, playerStub);
        expect(authServiceImpl.getUser(id)).not.toEqual(originalStub);
        expect(authServiceImpl.getUser(id)).toEqual(Object.assign({}, originalStub, {id: id}));
    });
});