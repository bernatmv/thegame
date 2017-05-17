import RoomModel from '../../src/common/service/models/roomModel';

export default new RoomModel(
    'test-01',
    'TestRoom',
    'This is the test room',
    {
        north: 'test-02',
        east: null,
        south: null,
        west: null,
        up: null, 
        down: null
    },
    [],
    [],
    [],
    []
);