import ConnectionData from '../../../common/stream/models/connectionData';

interface LoginSectionProps {
    connection: ConnectionData;
    placeholder?: string;
    login: (userId: string) => any;
}
export default LoginSectionProps;