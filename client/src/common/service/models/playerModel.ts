import ProfileModel from './profileModel';
//import ItemModel from './itemModel';
import { debug } from './appLogger';

export default class PlayerModel {
    public isAuthenticated: boolean;

    constructor(
        public id: string,
        public profile: ProfileModel
//        public experience: number,
//        public items: Array<ItemModel>,
//        public money: number
    ) {
        this.isAuthenticated = false;
        //debug(`New player created`, this);
    };
}