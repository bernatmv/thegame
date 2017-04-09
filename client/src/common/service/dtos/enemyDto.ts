import ProfileDto from './profileDto';

interface EnemyDto {
    id: string;
    profile: ProfileDto;
    chatter: Array<string>;
}
export default EnemyDto;