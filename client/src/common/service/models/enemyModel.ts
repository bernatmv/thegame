import ProfileModel from './profileModel';
//import ItemModel from './itemModel';
import { debug } from './appLogger';

export default class EnemyModel {
    constructor(
        public id: string,
        public profile: ProfileModel,
//        public belongsTo: Array<string>,
        public chatter: Array<string>
//        public loot: Array<ItemModel>,
//        public experienceValue: number
//        public money: number
    ) {
        //debug(`New enemy created`, this);
    };
}