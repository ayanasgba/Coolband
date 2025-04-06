package kg.geeks.coolband.mapper;

import javax.annotation.processing.Generated;
import kg.geeks.coolband.dto.request.StStudentReviewsRequest;
import kg.geeks.coolband.dto.response.StStudentReviewsResponse;
import kg.geeks.coolband.entities.StStudentReviews;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-06T14:41:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
public class StStudentReviewsMapperImpl implements StStudentReviewsMapper {

    @Override
    public StStudentReviews mapRequestToResponse(StStudentReviewsRequest request) {
        if ( request == null ) {
            return null;
        }

        StStudentReviews stStudentReviews = new StStudentReviews();

        stStudentReviews.setBluer( request.getBluer() );
        stStudentReviews.setName( request.getName() );
        stStudentReviews.setReviews( request.getReviews() );
        stStudentReviews.setOrientation( request.getOrientation() );

        return stStudentReviews;
    }

    @Override
    public StStudentReviewsResponse map(StStudentReviews entity) {
        if ( entity == null ) {
            return null;
        }

        StStudentReviewsResponse.StStudentReviewsResponseBuilder stStudentReviewsResponse = StStudentReviewsResponse.builder();

        stStudentReviewsResponse.id( entity.getId() );
        stStudentReviewsResponse.name( entity.getName() );
        stStudentReviewsResponse.image( entity.getImage() );
        stStudentReviewsResponse.bluer( entity.getBluer() );
        stStudentReviewsResponse.reviews( entity.getReviews() );
        stStudentReviewsResponse.orientation( entity.getOrientation() );

        return stStudentReviewsResponse.build();
    }
}
