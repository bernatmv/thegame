export default class StatusPropertyModel {
    constructor(
        public current: number,
        public max: number,
        public hide = false
    ) {}
}