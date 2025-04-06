package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.request.StTeachersRequest;
import kg.geeks.coolband.dto.response.StTeachersResponse;
import kg.geeks.coolband.entities.StTeachers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class StTeachersMapperImpl implements StTeachersMapper {

    @Override
    public StTeachers mapRequestToResponse(StTeachersRequest request) {
        if ( request == null ) {
            return null;
        }

        StTeachers.StTeachersBuilder stTeachers = StTeachers.builder();

        stTeachers.name( request.getName() );
        stTeachers.bluer( request.getBluer() );
        stTeachers.urlInstagram( request.getUrlInstagram() );
        stTeachers.position( request.getPosition() );
        stTeachers.description( request.getDescription() );
        stTeachers.orientation( request.getOrientation() );

        return stTeachers.build();
    }

    @Override
    public StTeachersResponse map(StTeachers entity) {
        if ( entity == null ) {
            return null;
        }

        StTeachersResponse.StTeachersResponseBuilder stTeachersResponse = StTeachersResponse.builder();

        stTeachersResponse.id( entity.getId() );
        stTeachersResponse.name( entity.getName() );
        stTeachersResponse.image( entity.getImage() );
        stTeachersResponse.bluer( entity.getBluer() );
        stTeachersResponse.urlInstagram( entity.getUrlInstagram() );
        stTeachersResponse.position( entity.getPosition() );
        stTeachersResponse.description( entity.getDescription() );
        stTeachersResponse.orientation( entity.getOrientation() );

        return stTeachersResponse.build();
    }
}
