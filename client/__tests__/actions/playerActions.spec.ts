import {move} from '../../src/app/actions/playerActions';
import ActionsConstants from '../../src/common/constants/actionsConstants';

describe('player actions', () => {

    describe('if we create a move action', () => {
        let direction = 'north';

        it('should generate action with payload', () => {
            expect(move(direction)).toEqual({
                type: ActionsConstants.Move,
                payload: direction
            });
        });
    });

    describe('if we create a move action without a userId', () => {
        const error = new TypeError('not a string');

        it('should fail', () => {
            expect(move(error)).toEqual({
                type: ActionsConstants.Move,
                payload: error,
                error: true
            });
        });
    });
});