import PlayerModel from './models/playerModel';

interface AuthService {
    login(id: string): PlayerModel;
}
export default AuthService;