import MapService from './mapService';
import RoomModel from '../../../common/service/models/roomModel';

export default class MapServiceImpl implements MapService {
    loadWorld(firstRoom: RoomModel): void {
        //TODO: implement
    }
    getRoom(id: string): RoomModel {
        //TODO: implement
        return {
            id: 'beta-room-0001',
            title: 'Un claro en el bosque',
            shortDescription: 'Un tranquilo claro en medio de un frondoso bosque',
            description: 'Te encuentras en un claro en medio de un frondoso bosque, un pequeño arrollo murmulla en un rincón mientras una suave brisa hace ondular varios tallos de lavanda llenando el aire de un suave aroma.\n\nAl norte los arboles se abren ligeramente a un sendero que parece poco transitado.',//tslint:disable-line
            exits: {
                north: 'beta-room-0002',
                east: null,
                south: null,
                west: null,
                up: null,
                down: null
            },
            enemies: [],
            npc: [],
            items: []
        };
    }
    moveTo(from: string, direction: 'north' | 'south' | 'east' | 'west' | 'up' | 'down'): RoomModel {
        //TODO: implement
        return {
            id: 'beta-room-0001',
            title: 'Un claro en el bosque',
            shortDescription: 'Un tranquilo claro en medio de un frondoso bosque',
            description: 'Te encuentras en un claro en medio de un frondoso bosque, un pequeño arrollo murmulla en un rincón mientras una suave brisa hace ondular varios tallos de lavanda llenando el aire de un suave aroma.\n\nAl norte los arboles se abren ligeramente a un sendero que parece poco transitado.',//tslint:disable-line
            exits: {
                north: 'beta-room-0002',
                east: null,
                south: null,
                west: null,
                up: null,
                down: null
            },
            enemies: [],
            npc: [],
            items: []
        };
    }
}