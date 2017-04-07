import StatusPropertyDto from '../dtos/statusPropertyDto';
import StatusPropertyModel from '../models/statusPropertyModel';

export default class StatusPropertyMapper {
    public map(dto: StatusPropertyDto): StatusPropertyModel {
        return new StatusPropertyModel(
            dto.current,
            dto.max,
            dto.hide ? true : false
        );
    }
}