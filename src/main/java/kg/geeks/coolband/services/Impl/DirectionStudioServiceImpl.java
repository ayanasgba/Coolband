package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.config.WebConfig;
import kg.geeks.coolband.dto.request.DirectionStudioRequest;
import kg.geeks.coolband.dto.response.DirectionStudioResponse;
import kg.geeks.coolband.entities.DirectionStudio;
import kg.geeks.coolband.exceptions.BadCredentialException;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.DirectionStudioMapper;
import kg.geeks.coolband.repository.DirectionStudioRepository;
import kg.geeks.coolband.services.DirectionStudioService;
import kg.geeks.coolband.services.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class DirectionStudioServiceImpl implements DirectionStudioService {

    private static final String SERVER = "%s/api/direction";
    private static final String DIRNAME = "DirectionStudio";

    private final DirectionStudioRepository directionStudioRepository;

    private final ImageService imageService;

    @Override
    public List<DirectionStudioResponse> getAll() {
        return directionStudioRepository.getAll();
    }

    @Override
    public DirectionStudioResponse save(DirectionStudioRequest directionStudioRequest) {
        DirectionStudio directionStudio = DirectionStudioMapper.INSTANCE.mapRequestToResponse(directionStudioRequest);

        String imagePath = imageService.uploadOriginal(directionStudioRequest.getImage(), DIRNAME);

        directionStudio.setImagePath(imagePath);

        directionStudioRepository.save(directionStudio);

        directionStudio.setImage(SERVER.formatted(WebConfig.getServer()) + "/image/%s".formatted(directionStudio.getId()));

        directionStudioRepository.save(directionStudio);

        return DirectionStudioMapper.INSTANCE.map(directionStudio);
    }

    @Override
    public DirectionStudioResponse getById(Long id) {
        return directionStudioRepository.findDirectionStudioById(id).orElseThrow(()->
                new NotFoundException("Direction with id: %s not found".formatted(id)));
    }

    @Override
    public DirectionStudioResponse deleteById(Long id) {
        DirectionStudio directionStudio = directionStudioRepository.findById(id).orElseThrow(
                () -> new BadCredentialException("Direction with id: %s doesn't exist".formatted(id))
        );
        imageService.delete(directionStudio.getImagePath(),directionStudioRepository.findAllByImagePath());
        directionStudioRepository.deleteById(id);
        log.info("Direction is deleted");
        return DirectionStudioMapper.INSTANCE.map(directionStudio);
    }


    @Override
    public DirectionStudioResponse patch(Long id, DirectionStudioRequest directionStudioRequest) throws IllegalAccessException, NoSuchFieldException {
        DirectionStudio directionStudio = directionStudioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Direction studio id: %s not found".formatted(id))
        );

        String previousImage = directionStudio.getImagePath();

        Patch.patchWithMediaFields(id,directionStudioRequest,directionStudio,DIRNAME,SERVER);

        if (previousImage != null && directionStudioRequest.getImage() != null){
            imageService.delete(previousImage,directionStudioRepository.findAllByImagePath());
        }
        log.info("Direction studio is updated");
        return DirectionStudioMapper.INSTANCE.map(directionStudio);
    }

}