package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.response.AboutUsStudioResponse;
import kg.geeks.coolband.entities.AboutUsStudio;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class AboutUsStudioMapperImpl implements AboutUsStudioMapper {

    @Override
    public AboutUsStudioResponse map(AboutUsStudio entity) {
        if ( entity == null ) {
            return null;
        }

        AboutUsStudioResponse.AboutUsStudioResponseBuilder aboutUsStudioResponse = AboutUsStudioResponse.builder();

        aboutUsStudioResponse.id( entity.getId() );
        aboutUsStudioResponse.image( entity.getImage() );
        aboutUsStudioResponse.orientation( entity.getOrientation() );
        aboutUsStudioResponse.bluer( entity.getBluer() );
        aboutUsStudioResponse.youtubeUrl( entity.getYoutubeUrl() );

        return aboutUsStudioResponse.build();
    }
}
