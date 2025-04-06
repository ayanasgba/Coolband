package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.response.HeroBandResponse;
import kg.geeks.coolband.entities.HeroBand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HeroBandMapper {


    HeroBandMapper INSTANCE = Mappers.getMapper(HeroBandMapper.class);

    HeroBandResponse map(HeroBand entity);

}
