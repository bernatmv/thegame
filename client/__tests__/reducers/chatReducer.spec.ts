import reducer from '../../src/app/reducers/chatReducer';
import * as ChatActions from '../../src/app/actions/chatActions';

const MAX_MESSAGES = 100;
const OVERFLOW_MESSAGES = 110;

describe('chat reducer', () => {

    describe('if we send a chat message', () => {
        const currentState = reducer(undefined, ChatActions.sendChat({message: 'testing'}));

        it('should have one additional message', () => {
            expect(currentState.length).toEqual(2);
            expect(currentState[1].message).toEqual('testing');
        });

        it('message should have a received value', () => {
            expect(currentState[1].received).not.toBeNull();
        });
    });

    describe(`if we send ${OVERFLOW_MESSAGES} messages`, () => {
        let currentState;
        for (let i = 0; i < OVERFLOW_MESSAGES; i++) {
            currentState = reducer(currentState, ChatActions.sendChat({message: `testing ${i}`}));
        }

        it(`should have ${MAX_MESSAGES} message`, () => {
            expect(currentState.length).toEqual(MAX_MESSAGES);
            expect(currentState[0].message).toEqual(`testing ${OVERFLOW_MESSAGES - MAX_MESSAGES}`);
        });
    });

    describe(`if we receive a new message`, () => {
        const received = Date.now();
        const currentState = reducer(undefined, ChatActions.sendChat({message: 'received', received: received}));

        it('should have one additional message', () => {
            expect(currentState.length).toEqual(2);
            expect(currentState[1].message).toEqual('received');
        });

        it('message should have the provided received value', () => {
            expect(currentState[1].received).toBe(received);
        });
    });
});