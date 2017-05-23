import PlayerDto from '../../../common/service/dtos/playerDto';

interface CreateCharacterSectionProps {
    create: (player: PlayerDto) => any;
}
export default CreateCharacterSectionProps;