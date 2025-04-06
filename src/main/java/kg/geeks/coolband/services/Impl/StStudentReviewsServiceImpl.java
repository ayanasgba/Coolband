package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.config.WebConfig;
import kg.geeks.coolband.dto.request.StStudentReviewsRequest;
import kg.geeks.coolband.dto.response.StStudentReviewsResponse;
import kg.geeks.coolband.entities.StStudentReviews;
import kg.geeks.coolband.exceptions.BadCredentialException;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.StStudentReviewsMapper;
import kg.geeks.coolband.repository.StStudentReviewsRepository;
import kg.geeks.coolband.services.ImageService;
import kg.geeks.coolband.services.StStudentReviewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class StStudentReviewsServiceImpl implements StStudentReviewsService {

    private static final String SERVER = "%s/api/st_student_reviews";
    private static final String DIRNAME = "StudioStudentReviews";

    private final StStudentReviewsRepository stStudentReviewsRepository;

    private final ImageService imageService;

    @Override
    public StStudentReviewsResponse save(StStudentReviewsRequest stStudentReviewsRequest) {
        StStudentReviews stStudentReviews = StStudentReviewsMapper.INSTANCE.mapRequestToResponse(stStudentReviewsRequest);

        String imagePath = imageService.uploadCompressed(stStudentReviewsRequest.getImage(), DIRNAME);

        stStudentReviews.setImagePath(imagePath);

        stStudentReviewsRepository.save(stStudentReviews);

        stStudentReviews.setImage(SERVER.formatted(WebConfig.getServer()) + "/image/%s".formatted(stStudentReviews.getId()));

        stStudentReviewsRepository.save(stStudentReviews);

        return StStudentReviewsMapper.INSTANCE.map(stStudentReviews);
    }

    @Override
    public List<StStudentReviewsResponse> getAll() {
        return stStudentReviewsRepository.getAll();
    }


    @Override
    public StStudentReviewsResponse patch(Long stStudentReviewsId, StStudentReviewsRequest stStudentReviewsRequest) throws IllegalAccessException, NoSuchFieldException {
        StStudentReviews stStudentReviews = stStudentReviewsRepository.findById(stStudentReviewsId).orElseThrow(
                () -> new NotFoundException("Student review id: %s not found".formatted(stStudentReviewsId))
        );

        String previousImage = stStudentReviews.getImagePath();

        Patch.patchWithMediaFields(stStudentReviewsId,stStudentReviewsRequest,stStudentReviews,DIRNAME,SERVER);

        if (previousImage != null && stStudentReviewsRequest.getImage() != null){
            imageService.delete(previousImage,stStudentReviewsRepository.findAllByImagePath());
        }

        stStudentReviewsRepository.save(stStudentReviews);

        log.info("Student review is updated");
        return StStudentReviewsMapper.INSTANCE.map(stStudentReviews);
    }


    @Override
    public StStudentReviewsResponse deleteById(Long id) {
        StStudentReviews stStudentReviews = stStudentReviewsRepository.findById(id).orElseThrow(
                () -> new BadCredentialException("Student review with id: %s doesn't exist".formatted(id))
        );

        imageService.delete(stStudentReviews.getImagePath(),stStudentReviewsRepository.findAllByImagePath());

        stStudentReviewsRepository.deleteById(id);

        log.info("Student review is deleted");

        return StStudentReviewsMapper.INSTANCE.map(stStudentReviews);
    }



    @Override
    public StStudentReviewsResponse getById(Long id) {
        return stStudentReviewsRepository.getStStudentReviewsById(id).orElseThrow(() ->
                new NotFoundException("Studio Student Review with id: %s not found".formatted(id)));
    }

}
