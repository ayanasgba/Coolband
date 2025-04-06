package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.response.AboutUsStudioResponse;
import kg.geeks.coolband.entities.AboutUsStudio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AboutUsStudioMapper {

    AboutUsStudioMapper INSTANCE = Mappers.getMapper(AboutUsStudioMapper.class);

    AboutUsStudioResponse map(AboutUsStudio entity);

}
