import reducer from '../../src/app/reducers/systemReducer';
import SystemModel from '../../src/common/service/models/systemModel';
import * as SystemActions from '../../src/app/actions/systemActions';

describe('reducer >> system', () => {

    describe('if we finish the intro', () => {
        const currentState = reducer(undefined, SystemActions.endIntro());

        it('should have state defined', () => {
            expect(currentState).toBeDefined();
        });

        it('should have playedIntro flag to true', () => {
            expect(currentState.playedIntro).toBe(true);
        });
    });
});