import EnemyDto from '../dtos/enemyDto';
import EnemyModel from '../models/enemyModel';
import ProfileMapper from './profileMapper';

export default class EnemyMapper {
    constructor(
        private _profileMapper: ProfileMapper
    ) {}

    public map(dto: EnemyDto): EnemyModel {
        return new EnemyModel(
            dto.id,
            this._profileMapper.map(dto.profile),
            dto.chatter
        );
    }
}