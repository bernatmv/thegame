import ItemDto from '../dtos/itemDto';
import ItemModel from '../models/itemModel';
import i18nService from '../i18nServiceImpl';

export default class ItemMapper {
    public map(dto: ItemDto): ItemModel {
        return new ItemModel(
            dto.id,
            i18nService.Instance.translate(dto.name),
            i18nService.Instance.translate(dto.description),
            dto.alive,
            dto.gender,
            i18nService.Instance.translate(dto.singular),
            i18nService.Instance.translate(dto.plural),
            dto.chatter
        );
    }
}