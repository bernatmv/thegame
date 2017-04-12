import ProfileDto from '../dtos/profileDto';
import ProfileModel from '../models/profileModel';
import StatusPropertyMapper from './statusPropertyMapper';
import i18nService from '../i18nServiceImpl';
import { nullable } from '../../helpers/functions';

export default class ProfileMapper {
    constructor(private _statusPropertyMapper: StatusPropertyMapper) {}

    public map(dto: ProfileDto): ProfileModel {
        return new ProfileModel(
            nullable(dto.name),
            i18nService.Instance.translate(dto.race),
            i18nService.Instance.translate(nullable(dto.singular)),
            i18nService.Instance.translate(nullable(dto.plural)),
            i18nService.Instance.translate(nullable(dto.profession)),
            dto.level,
            this._statusPropertyMapper.map(dto.hp),
            dto.mp ? this._statusPropertyMapper.map(dto.mp) : null,
            dto.sp ? this._statusPropertyMapper.map(dto.sp) : null
        );
    }
}