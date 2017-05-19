import reducer from '../../src/app/reducers/roomReducer';
import RoomModel from '../../src/common/service/models/roomModel';
import {playerStubFactory} from '../stubs/playerStub';
import roomStub from '../stubs/roomStub';
import * as SystemActions from '../../src/app/actions/systemActions';
import * as PlayerActions from '../../src/app/actions/playerActions';

describe('reducer >> room', () => {

    describe('if we load a room', () => {
        const currentState = reducer(undefined, SystemActions.loadRoom(roomStub));

        it('the state should not be null', () => {
            expect(currentState).not.toBeNull();
        });

        it('should return a valid room model', () => {
            expect(currentState).toBeInstanceOf(RoomModel);
        });

        it('should return the right room', () => {
            expect(currentState.id).toEqual('test-01');
        });

        it('should have the right exits', () => {
            expect(currentState.exits.north).toEqual('test-02');
            expect(currentState.exits.east).toBeNull();
            expect(currentState.exits.south).toBeNull();
            expect(currentState.exits.west).toBeNull();
            expect(currentState.exits.up).toBeNull();
            expect(currentState.exits.down).toBeNull();
        });
    });

    describe('if a player leaves the room', () => {
        const testUser1 = playerStubFactory('TestUser-1');
        const testUser2 = playerStubFactory('TestUser-2');
        const testUser3 = playerStubFactory('TestUser-3');
        roomStub.players = [testUser1, testUser2, testUser3];

        describe('and the player was in the room', () => {
            const currentState = reducer(roomStub, SystemActions.playerLeavesRoom({user: testUser2, exit: 'north'}));

            it('the room should remain the same', () => {
                expect(currentState).not.toBeNull();
                expect(currentState.id).toBe(roomStub.id);
            });

            it('should have one player less in the room', () => {
                expect(currentState.players.length).toBe(2);
            });

            it('should have the right players in the room', () => {
                expect(currentState.players[0].id).toBe(testUser1.id);
                expect(currentState.players[1].id).toBe(testUser3.id);
            });
        });

        describe('and the player was not in the room', () => {
            const currentState = reducer(roomStub, SystemActions.playerLeavesRoom({user: 'unknown-user', exit: 'north'}));

            it('the room should remain the same', () => {
                expect(currentState).not.toBeNull();
                expect(currentState.id).toBe(roomStub.id);
            });

            it('should have the same number of players in the room', () => {
                expect(currentState.players.length).toBe(3);
            });

            it('should have the right players in the room', () => {
                expect(currentState.players[0].id).toBe(testUser1.id);
                expect(currentState.players[1].id).toBe(testUser2.id);
                expect(currentState.players[2].id).toBe(testUser3.id);
            });
        });

        describe('and then all the remaining players leaves too', () => {
            let currentState = reducer(roomStub, SystemActions.playerLeavesRoom({user: testUser1, exit: 'north'}));
            currentState = reducer(currentState, SystemActions.playerLeavesRoom({user: testUser2, exit: 'north'}));
            currentState = reducer(currentState, SystemActions.playerLeavesRoom({user: testUser3, exit: 'north'}));

            it('the room should remain the same', () => {
                expect(currentState).not.toBeNull();
                expect(currentState.id).toBe(roomStub.id);
            });

            it('should be empty', () => {
                expect(currentState.players.length).toBe(0);
            });
        });
    });

    describe('if a player enters the room', () => {
        const testUser1 = playerStubFactory('TestUser-1');
        const testUser2 = playerStubFactory('TestUser-2');
        const testUser3 = playerStubFactory('TestUser-3');
        roomStub.players = [];

        describe('and the player was empty', () => {
            const currentState = reducer(roomStub, SystemActions.playerEntersRoom({user: testUser1, from: 'north'}));

            it('the room should remain the same', () => {
                expect(currentState).not.toBeNull();
                expect(currentState.id).toBe(roomStub.id);
            });

            it('should have one player more in the room', () => {
                expect(currentState.players.length).toBe(1);
            });

            it('should have the right players in the room', () => {
                expect(currentState.players[0].id).toBe(testUser1.id);
            });
        });

        describe('and the room was not empty', () => {
            roomStub.players = [testUser2];
            let currentState = reducer(roomStub, SystemActions.playerEntersRoom({user: testUser1, from: 'north'}));
            currentState = reducer(currentState, SystemActions.playerEntersRoom({user: testUser3, from: 'north'}));

            it('the room should remain the same', () => {
                expect(currentState).not.toBeNull();
                expect(currentState.id).toBe(roomStub.id);
            });

            it('should have the right number of players in the room', () => {
                expect(currentState.players.length).toBe(3);
            });

            it('should have the right players in the room', () => {
                expect(currentState.players[0].id).toBe(testUser2.id);
                expect(currentState.players[1].id).toBe(testUser1.id);
                expect(currentState.players[2].id).toBe(testUser3.id);
            });
        });

        describe('and he was already in the room!!', () => {
            roomStub.players = [testUser1];
            let currentState = reducer(roomStub, SystemActions.playerEntersRoom({user: testUser1, from: 'north'}));

            it('the room should remain the same', () => {
                expect(currentState).not.toBeNull();
                expect(currentState.id).toBe(roomStub.id);
            });

            it('should not add the player', () => {
                expect(currentState.players.length).toBe(1);
                expect(currentState.players[0].id).toBe(testUser1.id);
            });
        });
    });

    /**
     * THIS ACTION IS INTERCEPTED AND PROCESSED BY THE MIDDLEWARE SO IT DOES NOTHING
     */
    describe('if we give action to move to another room', () => {
        const currentState = reducer(roomStub, PlayerActions.move('north'));

        it('everything should be the same (!!INTERCEPTED BY MIDDLEWARE!!)', () => {
            expect(currentState).toBe(roomStub);
        });
    });
});