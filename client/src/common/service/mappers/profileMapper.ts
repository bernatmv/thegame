import ProfileDto from '../dtos/profileDto';
import ProfileModel from '../models/profileModel';
import StatusPropertyMapper from './statusPropertyMapper';
import { nullable } from '../../helpers/functions';

export default class ProfileMapper {
    constructor(private _statusPropertyMapper: StatusPropertyMapper) {}

    public map(dto: ProfileDto): ProfileModel {
        return new ProfileModel(
            nullable(dto.name),
            dto.race,
            nullable(dto.singular),
            nullable(dto.plural),
            nullable(dto.profession),
            dto.level,
            this._statusPropertyMapper.map(dto.hp),
            dto.mp ? this._statusPropertyMapper.map(dto.mp) : null,
            dto.sp ? this._statusPropertyMapper.map(dto.sp) : null
        );
    }
}