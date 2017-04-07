import PlayerDto from '../dtos/playerDto';
import PlayerModel from '../models/playerModel';
import ProfileMapper from './profileMapper';

export default class PlayerMapper {
    constructor(
        private _profileMapper: ProfileMapper
    ) {}

    public map(dto: PlayerDto): PlayerModel {
        return new PlayerModel(
            dto.id,
            this._profileMapper.map(dto.profile)
        );
    }
}