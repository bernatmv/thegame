import ProfileModel from './profileModel';
//import ItemModel from './itemModel';
import { debug } from './appLogger';

export default class PlayerModel {
    constructor(
        public id: string,
        public profile: ProfileModel,
//        public experience: number,
        public chatter: Array<string>
//        public items: Array<ItemModel>,
//        public money: number
    ) {
        //debug(`New player created`, this);
    };
}