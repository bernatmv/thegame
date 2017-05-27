import StatusPropertyMapper from '../../../src/common/service/mappers/statusPropertyMapper';
import ProfileMapper from '../../../src/common/service/mappers/profileMapper';
import PlayerMapper from '../../../src/common/service/mappers/playerMapper';
import EnemyMapper from '../../../src/common/service/mappers/enemyMapper';
import NPCMapper from '../../../src/common/service/mappers/npcMapper';
import ItemMapper from '../../../src/common/service/mappers/itemMapper';
import RoomMapper from '../../../src/common/service/mappers/roomMapper';
import RoomDto from '../../../src/common/service/dtos/roomDto';
import RoomModel from '../../../src/common/service/models/roomModel';
import EnemyModel from '../../../src/common/service/models/enemyModel';
import ItemModel from '../../../src/common/service/models/itemModel';
import dto from '../../stubs/dtos/roomDto';

describe('service >> mappers >> RoomMapper', () => {

    describe('if we create a new instance', () => {
        const profileMapper = new ProfileMapper(new StatusPropertyMapper());
        const mapper = new RoomMapper(
            new PlayerMapper(profileMapper),
            new EnemyMapper(profileMapper),
            new NPCMapper(profileMapper),
            new ItemMapper()
        );
        const model = mapper.map(<RoomDto>dto);

        it('should map a dto into a model', () => {
            expect(model).toBeDefined();
            expect(model).toBeInstanceOf(RoomModel);
        });

        it('should be the right room', () => {
            expect(model.id).toBe('beta-room-003');
        });

        it('should return a full room if we load one', () => {
            expect(model.id).toBeDefined();
            expect(model.title).toBeDefined();
            expect(model.description).toBeDefined();
            expect(model.exits).toBeDefined();
            expect(model.players).toBeDefined();
            expect(model.enemies).toBeDefined();
            expect(model.npc).toBeDefined();
            expect(model.items).toBeDefined();
        });

        it('should have the right exits', () => {
            expect(model.exits.north).toBe(null);
            expect(model.exits.east).toBe(null);
            expect(model.exits.south).toBe('beta-room-002');
            expect(model.exits.west).toBe(null);
            expect(model.exits.up).toBe(null);
            expect(model.exits.down).toBe(null);
        });

        it('should have enemies', () => {
            expect(model.enemies.length).toBe(2);
        });

        it('should have different enemies', () => {
            expect(model.enemies[0]).toBeInstanceOf(EnemyModel);
            expect(model.enemies[0].profile.level).toBe(2);
            expect(model.enemies[1]).toBeInstanceOf(EnemyModel);
            expect(model.enemies[1].profile.level).toBe(1);
        });

        it('should have the first enemy as a Goblin', () => {
            expect(model.enemies[0]).toBeInstanceOf(EnemyModel);
            expect(model.enemies[0].id).toBe('goblin-001');
        });

        it('should have one item', () => {
            expect(model.items.length).toBe(1);
        });

        it('should have Pascual the chicken as item', () => {
            expect(model.items[0]).toBeInstanceOf(ItemModel);
            expect(model.items[0].id).toBe('chicken-001');
            expect(model.items[0].name).toBe('Pascual');
        });
    });
});