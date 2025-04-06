package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.response.HeroStudioResponse;
import kg.geeks.coolband.entities.HeroStudio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HeroStudioMapper {

    HeroStudioMapper INSTANCE = Mappers.getMapper(HeroStudioMapper.class);

    HeroStudioResponse map(HeroStudio entity);
}
