import {loadRoom, playerEntersRoom, playerLeavesRoom, endIntro} from '../../src/app/actions/systemActions';
import ActionsConstants from '../../src/common/constants/actionsConstants';
import roomStub from '../stubs/roomStub';
import enterRoomStub from '../stubs/enterRoomStub';
import leaveRoomStub from '../stubs/leaveRoomStub';

describe('system actions', () => {
    describe('if we create a loadRoom action', () => {
        it('should generate action with payload', () => {
            expect(loadRoom(roomStub)).toEqual({
                type: ActionsConstants.LoadRoom,
                payload: roomStub
            });
        });
    });

    describe('if we create a loadRoom action without a userId', () => {
        const error = new TypeError('not a RoomModel');

        it('should fail', () => {
            expect(loadRoom(error)).toEqual({
                type: ActionsConstants.LoadRoom,
                payload: error,
                error: true
            });
        });
    });

    describe('if we create a playerEntersRoom action', () => {
        it('should generate action with payload', () => {
            expect(playerEntersRoom(enterRoomStub)).toEqual({
                type: ActionsConstants.PlayerEntersRoom,
                payload: enterRoomStub
            });
        });
    });

    describe('if we create a playerEntersRoom action without a userId', () => {
        const error = new TypeError('not a EnterRoomModel');

        it('should fail', () => {
            expect(playerEntersRoom(error)).toEqual({
                type: ActionsConstants.PlayerEntersRoom,
                payload: error,
                error: true
            });
        });
    });

    describe('if we create a playerLeavesRoom action', () => {
        it('should generate action with payload', () => {
            expect(playerLeavesRoom(leaveRoomStub)).toEqual({
                type: ActionsConstants.PlayerLeavesRoom,
                payload: leaveRoomStub
            });
        });
    });

    describe('if we create a playerLeavesRoom action without a userId', () => {
        const error = new TypeError('not a EnterRoomModel');

        it('should fail', () => {
            expect(playerLeavesRoom(error)).toEqual({
                type: ActionsConstants.PlayerLeavesRoom,
                payload: error,
                error: true
            });
        });
    });

    describe('if we create a endIntro action', () => {
        it('should generate action with payload', () => {
            expect(endIntro()).toEqual({
                type: ActionsConstants.EndIntro
            });
        });
    });
});