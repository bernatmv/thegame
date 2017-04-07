import { debug } from './appLogger';

export default class ItemModel {
    constructor(
        public id: string,
        public name: string,
        public decription: string,
//        public value: number,
//        public damage: number,
//        public speed: number,
//        public quality: number,
//        public rarity: number,        
        public isAlive: boolean,
        public gender: string,
        public singular: string,
        public plural: string,
        public chatter: Array<string>
    ) {
        //debug(`New item created`, this);
    };
}