import ProfileModel from './profileModel';
//import ItemModel from './itemModel';
import { debug } from './appLogger';

export default class NPCModel {
    constructor(
        public id: string,
        public profile: ProfileModel,
//        public belongsTo: Array<string>,
//        public quests: Array<string>,
        public chatter: Array<string>
//        public money: number,
//        public itemsForSell: Array<ItemModel>,
//        public buysItems: boolean
    ) {
        //debug(`New npc created`, this);
    };
}