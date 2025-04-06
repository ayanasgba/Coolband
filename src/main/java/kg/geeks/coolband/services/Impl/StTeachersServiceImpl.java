package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.config.WebConfig;
import kg.geeks.coolband.dto.request.StTeachersRequest;
import kg.geeks.coolband.dto.response.StTeachersResponse;
import kg.geeks.coolband.entities.StTeachers;
import kg.geeks.coolband.exceptions.BadCredentialException;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.StTeachersMapper;
import kg.geeks.coolband.repository.StTeachersRepository;
import kg.geeks.coolband.services.ImageService;
import kg.geeks.coolband.services.StTeachersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StTeachersServiceImpl implements StTeachersService {

    private static final String SERVER = "%s/api/st_teachers";
    private static final String DIRNAME = "StudioTeachers";

    private final ImageService imageService;

    private final StTeachersRepository stTeachersRepository;

    @Override
    public List<StTeachersResponse> getAll() {
        return stTeachersRepository.getAll();
    }

    @Override
    public StTeachersResponse save(StTeachersRequest stTeachersRequest) {
        StTeachers stTeachers = StTeachersMapper.INSTANCE.mapRequestToResponse(stTeachersRequest);
        String imagePath = imageService.uploadCompressed(stTeachersRequest.getImage(), DIRNAME);

        stTeachers.setImagePath(imagePath);
        stTeachersRepository.save(stTeachers);

        stTeachers.setImage(SERVER.formatted(WebConfig.getServer()) + "/image/%s".formatted(stTeachers.getId()));
        stTeachersRepository.save(stTeachers);

        log.info("Studio Teacher saved !");

        return StTeachersMapper.INSTANCE.map(stTeachers);
    }

    @Override
    public StTeachersResponse getById(Long id) {
        return stTeachersRepository.getStTeachersById(id).orElseThrow(() ->
                new NotFoundException("Studio Teacher with id: %s not found".formatted(id)));
    }

    @Override
    public StTeachersResponse deleteById(Long id) {
        StTeachers stTeachers = stTeachersRepository.findById(id).orElseThrow(
                () -> new BadCredentialException("Studio Teacher with id: %s doesn't exist".formatted(id))
        );

        imageService.delete(stTeachers.getImagePath(),stTeachersRepository.findAllByImagePath());

        stTeachersRepository.deleteById(id);

        log.info("Studio Teacher deleted !");

        return StTeachersMapper.INSTANCE.map(stTeachers);
    }

    public StTeachersResponse patch(Long stTeachersId, StTeachersRequest stTeachersRequest) throws IllegalAccessException, NoSuchFieldException {
        StTeachers stTeachers = stTeachersRepository.findById(stTeachersId).orElseThrow(
                () -> new NotFoundException("Studio Teachers ID : %s not found:".formatted(stTeachersId)));

        String previousImage = stTeachers.getImagePath();

        Patch.patchWithMediaFields(stTeachersId, stTeachersRequest, stTeachers, DIRNAME, SERVER);

        if (previousImage != null && stTeachersRequest.getImage() != null) {
            imageService.delete(previousImage,stTeachersRepository.findAllByImagePath());
        }

        stTeachersRepository.save(stTeachers);

        log.info("Studio Teacher updated !");

        return StTeachersMapper.INSTANCE.map(stTeachers);
    }
}
