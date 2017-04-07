import StatusPropertyModel from './statusPropertyModel';

export default class ProfileModel {
    constructor(
        public name: string,
//        public longName: string,
        public race: string,
        public singular: string,
        public plural: string,
        public profession: string,
        public level: number,
//        public titles: Array<string>,
//        public skills: Array<string>,
        public hp: StatusPropertyModel,
        public mp: StatusPropertyModel,
        public sp: StatusPropertyModel
    ) {};
}