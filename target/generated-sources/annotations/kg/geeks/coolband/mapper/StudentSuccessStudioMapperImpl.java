package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.request.StudentSuccessStudioRequest;
import kg.geeks.coolband.dto.response.StudentSuccessStudioResponse;
import kg.geeks.coolband.entities.StudentSuccessStudio;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class StudentSuccessStudioMapperImpl implements StudentSuccessStudioMapper {

    @Override
    public StudentSuccessStudio mapRequestToResponse(StudentSuccessStudioRequest request) {
        if ( request == null ) {
            return null;
        }

        StudentSuccessStudio studentSuccessStudio = new StudentSuccessStudio();

        studentSuccessStudio.setUrl( request.getUrl() );

        return studentSuccessStudio;
    }

    @Override
    public StudentSuccessStudioResponse map(StudentSuccessStudio entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String url = null;

        id = entity.getId();
        url = entity.getUrl();

        StudentSuccessStudioResponse studentSuccessStudioResponse = new StudentSuccessStudioResponse( id, url );

        return studentSuccessStudioResponse;
    }
}
