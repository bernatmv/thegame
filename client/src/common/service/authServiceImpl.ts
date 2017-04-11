import AuthService from './authService';
import PlayerModel from './models/playerModel';
import ProfileModel from './models/profileModel';
import StatusPropertyModel from './models/statusPropertyModel';
import RaceConstants from '../constants/raceConstants';
import { debug } from './models/appLogger';

export default class AuthServiceImpl implements AuthService {
    login(id: string): PlayerModel {
        //TODO: retrieve it from the server
        return new PlayerModel(
            id,
            new ProfileModel(
                id,
                RaceConstants.Human,
                'humano',
                'humanos',
                null,
                1,
                new StatusPropertyModel(100, 100),
                new StatusPropertyModel(100, 100),
                new StatusPropertyModel(100, 100)
            )
        );
    }
}