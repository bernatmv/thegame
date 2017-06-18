import RoomsRegistry from '../../src/common/helpers/roomsRegistry';
import RoomModel from '../../src/common/service/models/roomModel';
import * as BetaRoomDto001 from '../../../assets/rooms/beta_room_001.json';

describe('helper >> rooms registry', () => {
    it('should be a singleton', () => {
        expect(RoomsRegistry.Instance).toBeDefined();
        expect(RoomsRegistry.Instance).toBeInstanceOf(RoomsRegistry);
    });

    it('map should not be empty', () => {
        expect(RoomsRegistry.Instance.map).toBeDefined();
        expect(RoomsRegistry.Instance.map.size).toBeGreaterThan(0);
    });

    it('map should hold an instance of room', () => {
        expect(RoomsRegistry.Instance.map.get(BetaRoomDto001.id)).toBeDefined();
    });

    it('map should hold instances of RoomModel', () => {
        expect(RoomsRegistry.Instance.map.get(BetaRoomDto001.id)).toBeInstanceOf(RoomModel);
    });
});