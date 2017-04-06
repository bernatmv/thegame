export default class ProfileModel {
    constructor(
        public name: string,
//        public longName: string,
        public race: string,
        public profession: string,
        public level: number,
//        public titles: Array<string>,
//        public skills: Array<string>,
        public hp: number,
        public mp: number,
        public sp: number
    ) {};
}