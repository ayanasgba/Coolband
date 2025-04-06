package kg.geeks.coolband.services.Impl;

import jakarta.transaction.Transactional;
import kg.geeks.coolband.config.WebConfig;
import kg.geeks.coolband.dto.request.EventBandImagesRequest;
import kg.geeks.coolband.dto.response.EventBandImagesResponse;
import kg.geeks.coolband.entities.EventBand;
import kg.geeks.coolband.entities.EventImagesBand;
import kg.geeks.coolband.exceptions.BadCredentialException;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.EventImagesBandMapper;
import kg.geeks.coolband.repository.EventBandRepository;
import kg.geeks.coolband.repository.EventImagesBandRepository;
import kg.geeks.coolband.services.EventBandImagesService;
import kg.geeks.coolband.services.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EventBandImagesServiceImpl implements EventBandImagesService {

    private static final String SERVER = "%s/api/event_band_images";

    private static final String DIRNAME = "EventBandImages";

    private final EventImagesBandRepository eventImagesRepository;

    private final EventBandRepository eventBandRepository;

    private final ImageService imageService;

    @Override
    public List<EventBandImagesResponse> getAll() {
        return eventImagesRepository.getAll();
    }

    @Override
    public EventBandImagesResponse save(EventBandImagesRequest eventBandImagesRequest) {
        Long albumId = eventBandImagesRequest.getAlbumId();

        EventBand eventBand = eventBandRepository.findById(albumId).orElseThrow(() ->
                new NotFoundException("Event Band with id: %s not found".formatted(albumId)));

        EventImagesBand eventImagesBand = EventImagesBandMapper.INSTANCE.mapRequestToResponse(eventBandImagesRequest);

        String compressedPath = imageService.uploadCompressed(eventBandImagesRequest.getImage(), DIRNAME + File.separator + "compressed");
        String originalPath = imageService.uploadOriginal(eventBandImagesRequest.getImage(), DIRNAME + File.separator + "original");

        eventImagesBand.setImagePath(compressedPath);
        eventImagesBand.setOriginalImagePath(originalPath);

        eventImagesRepository.save(eventImagesBand);


        eventImagesBand.setEventBand(eventBand);
        eventImagesBand.setImage(SERVER.formatted(WebConfig.getServer()) + "/compImage/%s".formatted(eventImagesBand.getId()));
        eventImagesBand.setOriginalImage(SERVER.formatted(WebConfig.getServer()) + "/origImage/%s".formatted(eventImagesBand.getId()));

        if (eventBandImagesRequest.isCoverImage()) {
            eventImagesRepository.updateAllByCoverImage(eventBand, eventImagesBand.getId());
            eventBand.setCoverImage(eventImagesBand.getImage());
            eventBand.setBluer(eventImagesBand.getBluer());
        } else if (eventImagesRepository.getAllByEventBandId(albumId).size() == 1) {
            eventImagesBand.setCoverImage(true);
            eventBand.setCoverImage(eventImagesBand.getImage());
            eventBand.setBluer(eventImagesBand.getBluer());
        }

        eventImagesRepository.save(eventImagesBand);

        log.info("Event band images saved !");

        return EventImagesBandMapper.INSTANCE.map(eventImagesBand);
    }

    @Override
    public EventBandImagesResponse getById(Long id) {
        return eventImagesRepository.getEventImagesBandById(id).orElseThrow(() ->
                new NotFoundException("Event image with id: %s not found".formatted(id)));
    }

    @Override
    public List<EventBandImagesResponse> getAllByAlbumId(Long id) {
        return eventImagesRepository.getAllByEventBandId(id);
    }

    @Override
    public EventBandImagesResponse patch(Long id, EventBandImagesRequest eventBandImagesRequest) throws NoSuchFieldException, IllegalAccessException {

        EventImagesBand eventImagesBand = eventImagesRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Event Images Band id: %s not found".formatted(id))
        );

        EventBand eventBand = eventImagesBand.getEventBand();

        boolean previousCover = eventImagesBand.isCoverImage();

        String previousCompImage = eventImagesBand.getImagePath();
        String previousOrigImage = eventImagesBand.getOriginalImagePath();

        eventImagesBand.setEventBand(eventBand);

        Patch.patchWithMediaFields(id, eventBandImagesRequest, eventImagesBand, DIRNAME, SERVER);

        if (previousCompImage != null && eventBandImagesRequest.getImage() != null) {
            imageService.delete(previousCompImage,eventImagesRepository.findAllByImagePath());
            imageService.delete(previousOrigImage,eventImagesRepository.findAllByOriginalImagePath());
        }

        if (eventBandImagesRequest.isCoverImage()) {
            eventImagesRepository.updateAllByCoverImage(eventBand, id);
            eventBand.setCoverImage(eventImagesBand.getImage());
            eventBand.setBluer(eventImagesBand.getBluer());
        } else if (previousCover) {
            eventImagesRepository.updateByCoverImageModifying(eventBand);
            eventBand.setCoverImage(eventImagesRepository.getAllByEventBandId(eventBand.getId()).get(0).getImage());
            eventBand.setBluer(eventImagesRepository.getAllByEventBandId(eventBand.getId()).get(0).getBluer());
        }

        log.info("Event Band Images is updated");

        eventImagesRepository.save(eventImagesBand);

        if (eventImagesRepository.getAllByEventBandId(eventBand.getId()).get(0).getId().equals(id)) {
            eventImagesBand.setCoverImage(true);
        }

        return EventImagesBandMapper.INSTANCE.map(eventImagesBand);

    }

    @Override
    public EventBandImagesResponse deleteById(Long id) {

        EventImagesBand eventImagesBand = eventImagesRepository.findById(id).orElseThrow(
                () -> new BadCredentialException("Event image with id: %s doesn't exist".formatted(id)
                ));

        EventBand eventBand = eventImagesBand.getEventBand();

        eventImagesBand.setEventBand(eventBand);


        if (new File(eventImagesBand.getImagePath()).exists()) {
            if (new File(eventImagesBand.getOriginalImagePath()).exists()) {
                imageService.delete(eventImagesBand.getImagePath(),eventImagesRepository.findAllByImagePath());
                imageService.delete(eventImagesBand.getOriginalImagePath(),eventImagesRepository.findAllByOriginalImagePath());
            } else {
                imageService.delete(eventImagesBand.getImagePath(),eventImagesRepository.findAllByImagePath());
            }
        } else if (new File(eventImagesBand.getOriginalImagePath()).exists()) {
            imageService.delete(eventImagesBand.getOriginalImagePath(),eventImagesRepository.findAllByOriginalImagePath());
        }

        eventImagesRepository.deleteById(id);

        if (eventImagesBand.isCoverImage() && !eventImagesRepository.getAllByEventBandId(eventBand.getId()).isEmpty()) {
            eventImagesRepository.updateByCoverImageModifying(eventBand);
            eventBand.setCoverImage(eventImagesRepository.getAllByEventBandId(eventBand.getId()).get(0).getImage());
            eventBand.setBluer(eventImagesRepository.getAllByEventBandId(eventBand.getId()).get(0).getBluer());
        } else if (eventImagesBand.isCoverImage()) {
            eventBand.setBluer(null);
            eventBand.setCoverImage(null);
        }

        log.info("Event image is deleted");
        return EventImagesBandMapper.INSTANCE.map(eventImagesBand);
    }

}