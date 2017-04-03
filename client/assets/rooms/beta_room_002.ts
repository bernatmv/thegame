import RoomModel from '../../src/common/service/models/roomModel';

export default new RoomModel(
    'beta-room-002',
    'Un claro en el bosque',
    'Un tranquilo claro en medio de un frondoso bosque',
    'Te encuentras en un claro en medio de un frondoso bosque, un pequeño arrollo murmulla en un rincón mientras una suave brisa hace ondular varios tallos de lavanda llenando el aire de un suave aroma.\n\nAl norte los arboles se abren ligeramente a un sendero que parece poco transitado.',//tslint:disable-line
    {
        north: null,
        east: null,
        south: 'beta-room-001',
        west: null,
        up: null,
        down: null
    },
    [],
    [],
    []
);