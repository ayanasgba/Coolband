package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.config.WebConfig;
import kg.geeks.coolband.dto.request.CollaborationsBandRequest;
import kg.geeks.coolband.dto.response.CollaborationsBandResponse;
import kg.geeks.coolband.entities.CollaborationsBand;
import kg.geeks.coolband.exceptions.BadCredentialException;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.CollaborationsBandMapper;
import kg.geeks.coolband.repository.CollaborationsBandRepository;
import kg.geeks.coolband.services.CollaborationsBandService;
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
public class CollaborationsBandImpl implements CollaborationsBandService {

    private static final String SERVER = "%s/api/collaborations_band";
    private static final String DIRNAME = "CollaborationsBand";

    private final ImageService imageService;

    private final CollaborationsBandRepository collaborationsBandRepository;

    @Override
    public List<CollaborationsBandResponse> getAll() {
        return collaborationsBandRepository.getAll();
    }

    @Override
    public CollaborationsBandResponse save(CollaborationsBandRequest collaborationsBandRequest) {
        CollaborationsBand collaborationsBand = CollaborationsBandMapper.INSTANCE.mapRequestToResponse(collaborationsBandRequest);

        String imagePath = imageService.uploadCompressed(collaborationsBandRequest.getImage(), DIRNAME);

        collaborationsBand.setImagePath(imagePath);
        collaborationsBandRepository.save(collaborationsBand);

        collaborationsBand.setImage(SERVER.formatted(WebConfig.getServer()) + "/image/%s".formatted(collaborationsBand.getId()));
        collaborationsBandRepository.save(collaborationsBand);

        log.info("Collaboration Band saved! ");

        return CollaborationsBandMapper.INSTANCE.map(collaborationsBand);
    }

    @Override
    public CollaborationsBandResponse getById(Long id) {
        return collaborationsBandRepository.getCollaborationsBandById(id).orElseThrow(() ->
                new NotFoundException("Collaborations band with id: %s not found".formatted(id)));
    }

    @Override
    public CollaborationsBandResponse deleteById(Long id) {
        CollaborationsBand collaborationsBand = collaborationsBandRepository.findById(id).orElseThrow(
                () -> new BadCredentialException("Collaborations band with id: %s doesn't exist".formatted(id))
        );

        imageService.delete(collaborationsBand.getImagePath(),collaborationsBandRepository.findAllByImagePath());

        collaborationsBandRepository.deleteById(id);

        log.info("Collaborations band is deleted");

        return CollaborationsBandMapper.INSTANCE.map(collaborationsBand);
    }

    @Override
    public CollaborationsBandResponse patch(Long collaborationsBandId, CollaborationsBandRequest collaborationsBandRequest) throws NoSuchFieldException, IllegalAccessException {
        CollaborationsBand collaborationsBand = collaborationsBandRepository.findById(collaborationsBandId).orElseThrow(() ->
                new NotFoundException("Collaborations band with id: %s not found".formatted(collaborationsBandId)));

        String previousImagePath = collaborationsBand.getImagePath();

        Patch.patchWithMediaFields(collaborationsBandId, collaborationsBandRequest, collaborationsBand, DIRNAME, SERVER);

        if (previousImagePath != null && collaborationsBandRequest.getImage() != null) {
            imageService.delete(previousImagePath,collaborationsBandRepository.findAllByImagePath());
        }

        collaborationsBandRepository.save(collaborationsBand);

        log.info("Collaborations band updated");

        return CollaborationsBandMapper.INSTANCE.map(collaborationsBand);


    }

}
