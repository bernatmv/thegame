import RoomDto from '../dtos/roomDto';
import RoomModel from '../models/roomModel';
import PlayerMapper from './playerMapper';
import EnemyMapper from './enemyMapper';
import NPCMapper from './npcMapper';
import ItemMapper from './itemMapper';
import i18nService from '../i18nServiceImpl';
import EnemiesRegistry from '../../helpers/enemiesRegistry';
import ItemsRegistry from '../../helpers/itemsRegistry';
import { nullable } from '../../helpers/functions';

export default class RoomMapper {
    constructor(
        private _playerMapper: PlayerMapper,
        private _enemyMapper: EnemyMapper,
        private _npcMapper: NPCMapper,
        private _itemMapper: ItemMapper
    ) {}

    public map(dto: RoomDto): RoomModel {
        return new RoomModel(
            dto.id,
            i18nService.Instance.translate(dto.title),
            i18nService.Instance.translate(dto.shortDescription),
            i18nService.Instance.translate(dto.description),
            {
                north: nullable(dto.exits.north),
                east: nullable(dto.exits.east),
                south: nullable(dto.exits.south),
                west: nullable(dto.exits.west),
                up: nullable(dto.exits.up),
                down: nullable(dto.exits.down)
            },
            dto.players.map(player => this._playerMapper.map(player)),
            dto.enemies.map(enemy => {
                let baseEnemy = EnemiesRegistry.Instance.map.get(enemy.id);
                let mergedDto = Object.assign({}, baseEnemy, enemy);
                mergedDto.profile = Object.assign({}, baseEnemy.profile, enemy.profile);
                return this._enemyMapper.map(mergedDto);
            }),
            dto.npc.map(npc => this._npcMapper.map(npc)),
            dto.items.map(item => this._itemMapper.map(Object.assign({}, ItemsRegistry.Instance.map.get(item.id), item)))
        );
    }
}