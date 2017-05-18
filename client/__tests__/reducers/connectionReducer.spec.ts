import reducer from '../../src/app/reducers/connectionReducer';
import ConnectionStatus from '../../src/common/stream/models/connectionStatus';
import * as ChatActions from '../../src/app/actions/chatActions';

describe('reducer >> connection', () => {

    describe('if we try to connect to a chat', () => {
        const currentState = reducer(undefined, ChatActions.connectingToChat());

        it('should have status "connecting"', () => {
            expect(currentState.connectionStatus).toBe(ConnectionStatus.Connecting);
        });
    });

    describe('when we connect to a chat', () => {
        const testUserId = 'TestUserId';
        const currentState = reducer(undefined, ChatActions.connectedToChat({userId: testUserId}));

        it('should have status "connected"', () => {
            expect(currentState.connectionStatus).toBe(ConnectionStatus.Connected);
        });

        it('should have userId', () => {
            expect(currentState.userId).toEqual(testUserId);
        });
    });

    describe('if we disconnect from a chat', () => {
        const currentState = reducer(undefined, ChatActions.disconnectedFromChat());

        it('should have status "disconnected"', () => {
            expect(currentState.connectionStatus).toBe(ConnectionStatus.Disconnected);
        });
    });
});