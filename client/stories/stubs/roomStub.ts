import {roomStubFactory} from '../../__tests__/stubs/roomStub';
import {playerStubFactory} from '../../__tests__/stubs/playerStub';
import {enemyStubFactory} from '../../__tests__/stubs/enemyStub';

const room = roomStubFactory({id: 'test-01', exits: {north: 'test-02'}});
room.players = [
    playerStubFactory('Tester-01'),
    playerStubFactory('Tester-02'),
    playerStubFactory('Tester-03')
];
room.enemies = [
    enemyStubFactory('Goblin-01'),
    enemyStubFactory('Goblin-02')
];

export default room;