package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.response.HeroStudioResponse;
import kg.geeks.coolband.entities.HeroStudio;
import kg.geeks.coolband.enums.Gallery;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class HeroStudioMapperImpl implements HeroStudioMapper {

    @Override
    public HeroStudioResponse map(HeroStudio entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String text = null;
        String video = null;
        Gallery orientation = null;

        id = entity.getId();
        title = entity.getTitle();
        text = entity.getText();
        video = entity.getVideo();
        orientation = entity.getOrientation();

        HeroStudioResponse heroStudioResponse = new HeroStudioResponse( id, title, text, video, orientation );

        return heroStudioResponse;
    }
}
