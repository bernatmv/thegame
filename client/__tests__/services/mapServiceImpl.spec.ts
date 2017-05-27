import MapServiceImpl from '../../src/common/service/mapServiceImpl';
import roomDto from '../stubs/dtos/roomDto';
import RoomDto from '../../src/common/service/dtos/roomDto';
import RoomModel from '../../src/common/service/models/roomModel';
import EnemyModel from '../../src/common/service/models/enemyModel';
import ItemModel from '../../src/common/service/models/itemModel';

describe('service >> MapServiceImpl', () => {
    const mapServiceImpl = new MapServiceImpl();
    let room = mapServiceImpl.loadRoom(<RoomDto>roomDto);

    it('should return an instance of RoomModel', () => {
        expect(room).toBeInstanceOf(RoomModel);
    });

    it('should be the right room', () => {
        expect(room.id).toBe('beta-room-003');
    });

    it('should return a full room if we load one', () => {
        expect(room.id).toBeDefined();
        expect(room.title).toBeDefined();
        expect(room.description).toBeDefined();
        expect(room.exits).toBeDefined();
        expect(room.players).toBeDefined();
        expect(room.enemies).toBeDefined();
        expect(room.npc).toBeDefined();
        expect(room.items).toBeDefined();
    });

    it('should have the right exits', () => {
        expect(room.exits.north).toBe(null);
        expect(room.exits.east).toBe(null);
        expect(room.exits.south).toBe('beta-room-002');
        expect(room.exits.west).toBe(null);
        expect(room.exits.up).toBe(null);
        expect(room.exits.down).toBe(null);
    });

    it('should have enemies', () => {
        expect(room.enemies.length).toBe(2);
    });

    it('should have different enemies', () => {
        expect(room.enemies[0]).toBeInstanceOf(EnemyModel);
        expect(room.enemies[0].profile.level).toBe(2);
        expect(room.enemies[1]).toBeInstanceOf(EnemyModel);
        expect(room.enemies[1].profile.level).toBe(1);
    });

    it('should have the first enemy as a Goblin', () => {
        expect(room.enemies[0]).toBeInstanceOf(EnemyModel);
        expect(room.enemies[0].id).toBe('goblin-001');
    });

    it('should have one item', () => {
        expect(room.items.length).toBe(1);
    });

    it('should have Pascual the chicken as item', () => {
        expect(room.items[0]).toBeInstanceOf(ItemModel);
        expect(room.items[0].id).toBe('chicken-001');
        expect(room.items[0].name).toBe('Pascual');
    });
});