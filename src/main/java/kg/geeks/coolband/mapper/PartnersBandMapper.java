package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.request.PartnersBandRequest;
import kg.geeks.coolband.dto.response.PartnersBandResponse;
import kg.geeks.coolband.entities.PartnersBand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PartnersBandMapper {

    PartnersBandMapper INSTANCE = Mappers.getMapper(PartnersBandMapper.class);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "imagePath",ignore = true)
    PartnersBand mapRequestToResponse(PartnersBandRequest request);

    PartnersBandResponse map(PartnersBand entity);

}
