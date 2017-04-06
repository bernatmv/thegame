import RoomModel from '../../src/common/service/models/roomModel';

export default new RoomModel(
    'beta-room-003',
    'La plaza del campamento',
    'La plaza central del campamento empalizado',
    'El campamento consiste en cuatro chozas grandes de paja que podrían alojar a cinco o seis personas cada una.\nTodas las chozas son de construcción basta y hechas con materiales de poca calidad. La plaza ocupa una extensión considerable donde cabrían sin problemas cien personas, pero en vez de eso sólo hay varios montones de suciedad y unas cuantas gallinas picoteando en ellos.',//tslint:disable-line
    {
        north: null,
        east: null,
        south: 'beta-room-002',
        west: null,
        up: null,
        down: null
    },
    [],
    [
        //TODO: transform this into roomDto with id's
        //TODO: create shared JSON with id's (dto)
        //TODO: map dto to room with entities
        //TODO: make registry load files automatically
    ],
    [],
    []
);