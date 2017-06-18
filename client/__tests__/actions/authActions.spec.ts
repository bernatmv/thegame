import {login, signup} from '../../src/app/actions/authActions';
import ActionsConstants from '../../src/common/constants/actionsConstants';

describe('auth actions', () => {

    describe('if we create a login action', () => {
        let userId = 'TestUser';

        it('should generate action with payload', () => {
            expect(login(userId)).toEqual({
                type: ActionsConstants.Login,
                payload: userId
            });
        });
    });

    describe('if we create a login action without a userId', () => {
        const error = new TypeError('not a string');

        it('should fail', () => {
            expect(login(error)).toEqual({
                type: ActionsConstants.Login,
                payload: error,
                error: true
            });
        });
    });

    describe('if we create a signup action', () => {
        it('should generate action with payload', () => {
            expect(signup()).toEqual({
                type: ActionsConstants.SignUp
            });
        });
    });
});