interface ItemDto {
    id: string;
    name: string;
    description: string;
    isAlive: boolean;
    gender: string;
    singular: string;
    plural: string;
    chatter: Array<string>;
}
export default ItemDto;