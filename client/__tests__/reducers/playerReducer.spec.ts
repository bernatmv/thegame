import reducer from '../../src/app/reducers/playerReducer';
import PlayerModel from '../../src/common/service/models/playerModel';
import * as AuthActions from '../../src/app/actions/authActions';

describe('reducer >> player', () => {

    describe('if we login as a user', () => {
        const testUserId = 'TestUserId';
        const currentState = reducer(undefined, AuthActions.login(testUserId));

        it('the state should not be null', () => {
            expect(currentState).not.toBeNull();
        });

        it('should return a valid player model', () => {
            expect(currentState).toBeInstanceOf(PlayerModel);
        });

        it('should return the right player', () => {
            expect(currentState.id).toEqual(testUserId);
        });
    });
});