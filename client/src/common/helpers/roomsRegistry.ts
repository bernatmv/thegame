import RoomModel from '../service/models/roomModel';
import RoomMapper from '../service/mappers/roomMapper';
import ProfileMapper from '../service/mappers/profileMapper';
import PlayerMapper from '../service/mappers/playerMapper';
import EnemyMapper from '../service/mappers/enemyMapper';
import NPCMapper from '../service/mappers/npcMapper';
import ItemMapper from '../service/mappers/itemMapper';

//TODO: CLEAN => Loading temporarily while we develop more the BE
import * as BetaRoomDto001 from '../../../../assets/rooms/beta_room_001.json';
import * as BetaRoomDto002 from '../../../../assets/rooms/beta_room_002.json';
import * as BetaRoomDto003 from '../../../../assets/rooms/beta_room_003.json';


export default class RoomsRegistry {
    private _roomMapper: RoomMapper;

    private static _instance: RoomsRegistry;
    public map: Map<string, RoomModel>;

    constructor() {
        //TODO: create a container for dependency injection
        let profileMapper = new ProfileMapper();
        this._roomMapper = new RoomMapper(
            new PlayerMapper(profileMapper),
            new EnemyMapper(profileMapper),
            new NPCMapper(profileMapper),
            new ItemMapper()
        );

        this.map = new Map<string, RoomModel>();
        this.map.set(BetaRoomDto001.id, BetaRoomDto001);
        this.map.set(BetaRoomDto002.id, BetaRoomDto002);
        this.map.set(BetaRoomDto003.id, BetaRoomDto003);
    }

    public static get Instance(): RoomsRegistry {
        if (!RoomsRegistry._instance) {
            RoomsRegistry._instance = new RoomsRegistry();
        }
        return RoomsRegistry._instance;
    }
}