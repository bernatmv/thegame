import { debug } from './appLogger';

export default class ItemModel {
    constructor(
        public id: string,
//        public value: number,
//        public damage: number,
//        public speed: number,
//        public quality: number,
//        public rarity: number,        
        public isAlive: boolean,
        public chatter: Array<string>
    ) {
        //debug(`New item created`, this);
    };
}