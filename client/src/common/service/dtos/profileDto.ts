import StatusPropertyDto from './statusPropertyDto';

interface ProfileDto {
    name?: string;
    race: string;
    singular?: string;
    plural?: string;
    profession?: string;
    level: number;
    hp: StatusPropertyDto;
    mp?: StatusPropertyDto;
    sp?: StatusPropertyDto;
}
export default ProfileDto;