import RoomModel from '../../src/common/service/models/roomModel';

export const roomStubFactory = ({id, exits}) => {
    return new RoomModel(
        id,
        'TestRoom',
        'This is the test room',
        {
            north: exits.north ? exits.north : null,
            east: exits.east ? exits.east : null,
            south: exits.south ? exits.south : null,
            west: exits.west ? exits.west : null,
            up: exits.up ? exits.up : null,
            down: exits.down ? exits.down : null
        },
        [],
        [],
        [],
        []
    );
};

export default roomStubFactory({id: 'test-01', exits: {north: 'test-02'}});