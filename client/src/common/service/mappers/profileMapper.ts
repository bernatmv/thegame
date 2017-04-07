import ProfileDto from '../dtos/profileDto';
import ProfileModel from '../models/profileModel';
import { nullable } from '../../helpers/functions';

export default class ProfileMapper {
    public map(dto: ProfileDto): ProfileModel {
        return new ProfileModel(
            nullable(dto.name),
            dto.race,
            nullable(dto.profession),
            dto.level,
            dto.hp,
            nullable(dto.mp),
            nullable(dto.sp)
        );
    }
}