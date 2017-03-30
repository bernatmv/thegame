import EnumBase from '../../service/models/enumBase';

export default class ConnectionStatus extends EnumBase {
    public static Disconnected: ConnectionStatus = new ConnectionStatus (1, 'Disconnected');
    public static Connecting: ConnectionStatus = new ConnectionStatus (2, 'Connecting');
    public static Connected: ConnectionStatus = new ConnectionStatus (3, 'Connected');
}