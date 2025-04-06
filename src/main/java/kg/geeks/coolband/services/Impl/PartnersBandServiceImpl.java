package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.config.WebConfig;
import kg.geeks.coolband.dto.request.PartnersBandRequest;
import kg.geeks.coolband.dto.response.PartnersBandResponse;
import kg.geeks.coolband.entities.PartnersBand;
import kg.geeks.coolband.exceptions.BadCredentialException;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.PartnersBandMapper;
import kg.geeks.coolband.repository.PartnersBandRepository;
import kg.geeks.coolband.services.ImageService;
import kg.geeks.coolband.services.PartnersBandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PartnersBandServiceImpl implements PartnersBandService {

    private static final String SERVER = "%s/api/partners_band";
    private static final String DIRNAME = "PartnersBand";

    private final PartnersBandRepository partnersBandRepository;

    private final ImageService imageService;

    @Override
    public List<PartnersBandResponse> getAll() {
        return partnersBandRepository.getAll();
    }

    @Override
    public PartnersBandResponse save(PartnersBandRequest partnersBandRequest) {
        PartnersBand partnersBand = PartnersBandMapper.INSTANCE.mapRequestToResponse(partnersBandRequest);

        String imagePath = imageService.uploadOriginal(partnersBandRequest.getImage(), DIRNAME);

        partnersBand.setImagePath(imagePath);

        partnersBandRepository.save(partnersBand);

        partnersBand.setImage(SERVER.formatted(WebConfig.getServer()) + "/image/%s".formatted(partnersBand.getId()));

        partnersBandRepository.save(partnersBand);

        return PartnersBandMapper.INSTANCE.map(partnersBand);
    }

    @Override
    public PartnersBandResponse getById(Long id) {
        return partnersBandRepository.findPartnersBandById(id).orElseThrow(() ->
                new NotFoundException("Partner with id: %s not found".formatted(id)));
    }

    @Override
    public PartnersBandResponse deleteById(Long id) {
        PartnersBandResponse partnersBandResponse = partnersBandRepository.findPartnersBandById(id).orElseThrow(
                () -> new BadCredentialException("Partner with id: %s doesn't exist".formatted(id))
        );
        partnersBandRepository.deleteById(id);
        log.info("Partner is deleted");
        return partnersBandResponse;
    }

    @Override
    public PartnersBandResponse patch(Long id, PartnersBandRequest partnersBandRequest) throws IllegalAccessException, NoSuchFieldException {
        PartnersBand partnersBand = partnersBandRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Partners band id: %s not found".formatted(id))
        );

        String previousImage = partnersBand.getImagePath();

        Patch.patchWithMediaFields(id,partnersBandRequest,partnersBand,DIRNAME,SERVER);

        if (previousImage != null && partnersBandRequest.getImage() != null){
            imageService.delete(previousImage,partnersBandRepository.findAllByImagePath());
        }
        log.info("Partners band is updated");
        return PartnersBandMapper.INSTANCE.map(partnersBand);
    }

}
