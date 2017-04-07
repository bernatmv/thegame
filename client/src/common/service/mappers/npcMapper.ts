import NPCDto from '../dtos/npcDto';
import NPCModel from '../models/npcModel';
import ProfileMapper from './profileMapper';

export default class NPCMapper {
    constructor(
        private _profileMapper: ProfileMapper
    ) {}

    public map(dto: NPCDto): NPCModel {
        return new NPCModel(
            dto.id,
            this._profileMapper.map(dto.profile),
            dto.chatter
        );
    }
}