import AuthService from './authService';
import PlayerModel from './models/playerModel';
import playerStub from '../../../__tests__/stubs/playerStub';
import { debug } from './models/appLogger';

export default class AuthServiceImpl implements AuthService {
    login(id: string): PlayerModel {
        //TODO: retrieve it from the server
        playerStub.id = id;
        return playerStub;
    }

    getUser(id: string): PlayerModel {
        //TODO: retrieve it from the server
        playerStub.id = id;
        return playerStub;
    }
}