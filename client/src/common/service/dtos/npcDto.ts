import ProfileDto from './profileDto';

interface NPCDto {
    id: string;
    profile: ProfileDto;
    chatter: Array<string>;
}
export default NPCDto;