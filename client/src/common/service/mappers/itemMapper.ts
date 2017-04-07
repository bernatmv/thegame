import ItemDto from '../dtos/itemDto';
import ItemModel from '../models/itemModel';

export default class ItemMapper {
    public map(dto: ItemDto): ItemModel {
        return new ItemModel(
            dto.id,
            dto.name,
            dto.decription,
            dto.isAlive,
            dto.gender,
            dto.singular,
            dto.plural,
            dto.chatter
        );
    }
}