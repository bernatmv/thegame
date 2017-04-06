import RoomModel from '../../src/common/service/models/roomModel';

export default new RoomModel(
    'beta-room-002',
    'Las afueras del campamento',
    'Entre el bosque y la pared de una colina, hay un pequeño campamento',
    'El camino lleva hasta un espacio abierto entre el bosque y la pared de una colina.\nEn este lugar hay un pequeño asentamiento rodeado por una empalizada de algo menos de dos metros. Los troncos de la empalizada son pequeños e irregulares, y están atados con primitivas cuerdas de cáñamo.',//tslint:disable-line
    {
        north: 'beta-room-003',
        east: null,
        south: 'beta-room-001',
        west: null,
        up: null,
        down: null
    },
    [],
    [],
    [],
    []
);