import StatusPropertyModel from '../../../../common/service/models/statusPropertyModel';

interface ProfileProps {
    name?: string;
    race: string;
    profession?: string;
    level: number;
    hp: StatusPropertyModel;
    mp?: StatusPropertyModel;
    sp?: StatusPropertyModel;
    size?: 'small' | 'medium';
}
export default ProfileProps;