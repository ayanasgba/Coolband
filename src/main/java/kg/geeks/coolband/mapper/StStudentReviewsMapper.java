package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.request.StStudentReviewsRequest;
import kg.geeks.coolband.dto.response.StStudentReviewsResponse;
import kg.geeks.coolband.entities.StStudentReviews;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StStudentReviewsMapper {

    StStudentReviewsMapper INSTANCE = Mappers.getMapper(StStudentReviewsMapper.class);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "image", ignore = true)
    @Mapping(target = "imagePath",ignore = true)
    StStudentReviews mapRequestToResponse(StStudentReviewsRequest request);

    StStudentReviewsResponse map(StStudentReviews entity);

}
