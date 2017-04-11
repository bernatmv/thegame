import ConnectionData from '../../../../common/stream/models/connectionData';

interface ConnectionStatusProps {
    connection: ConnectionData;
    top?: boolean;
    bottom?: boolean;
    left?: boolean;
    right?: boolean;
}
export default ConnectionStatusProps;