import RoomModel from '../../src/common/service/models/roomModel';
import BetaRoom001 from './beta_room_001';
import BetaRoom002 from './beta_room_002';
import BetaRoom003 from './beta_room_003';

export default class RoomsRegistry {
    private static _instance: RoomsRegistry;
    public map: Map<string, RoomModel>;

    constructor() {
        this.map = new Map<string, RoomModel>();
        this.map.set(BetaRoom001.id, BetaRoom001);
        this.map.set(BetaRoom002.id, BetaRoom002);
        this.map.set(BetaRoom003.id, BetaRoom003);
    }

    public static get Instance(): RoomsRegistry {
        if (!RoomsRegistry._instance) {
            RoomsRegistry._instance = new RoomsRegistry();
        }
        return RoomsRegistry._instance;
    }
}