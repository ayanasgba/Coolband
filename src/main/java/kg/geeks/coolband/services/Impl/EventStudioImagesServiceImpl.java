package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.config.WebConfig;
import kg.geeks.coolband.dto.request.EventStudioImagesRequest;
import kg.geeks.coolband.dto.response.EventStudioImagesResponse;
import kg.geeks.coolband.entities.EventImagesStudio;
import kg.geeks.coolband.entities.EventStudio;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.EventImagesStudioMapper;
import kg.geeks.coolband.repository.EventImagesStudioRepository;
import kg.geeks.coolband.repository.EventStudioRepository;
import kg.geeks.coolband.services.EventStudioImagesService;
import kg.geeks.coolband.services.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class
EventStudioImagesServiceImpl implements EventStudioImagesService {

    private static final String SERVER = "%s/api/event_studio_images";

    private static final String DIRNAME = "EventStudioImages";

    private final EventStudioRepository eventStudioRepository;

    private final EventImagesStudioRepository eventImagesStudioRepository;

    private final ImageService imageService;

    @Override
    public List<EventStudioImagesResponse> getAll() {
        return eventImagesStudioRepository.getAll();
    }

    @Override
    public EventStudioImagesResponse save(EventStudioImagesRequest eventStudioImagesRequest) {

        Long albumId = eventStudioImagesRequest.getAlbumId();

        EventStudio eventStudio = eventStudioRepository.findById(albumId).orElseThrow(() ->
                new NotFoundException("Event Studio with ID: %s not found".formatted(albumId)));

        EventImagesStudio eventImagesStudio = EventImagesStudioMapper.INSTANCE.mapRequestToResponse(eventStudioImagesRequest);

        String compressedPath = imageService.uploadCompressed(eventStudioImagesRequest.getImage(), DIRNAME + File.separator + "compressed");
        String originalImagePath = imageService.uploadOriginal(eventStudioImagesRequest.getImage(), DIRNAME + File.separator + "original");

        eventImagesStudio.setOriginalImagePath(originalImagePath);
        eventImagesStudio.setImagePath(compressedPath);

        eventImagesStudioRepository.save(eventImagesStudio);

        eventImagesStudio.setEventStudio(eventStudio);
        eventImagesStudio.setImage(SERVER.formatted(WebConfig.getServer()) + "/compImage/%s".formatted(eventImagesStudio.getId()));
        eventImagesStudio.setOriginalImage(SERVER.formatted(WebConfig.getServer()) + "/origImage/%s".formatted(eventImagesStudio.getId()));


        if (eventStudioImagesRequest.isCoverImage()) {
            eventImagesStudioRepository.updateAllByCoverImage(eventStudio,eventImagesStudio.getId());
            eventStudio.setCoverImage(eventImagesStudio.getImage());
            eventStudio.setBluer(eventImagesStudio.getBluer());
        } else if (eventImagesStudioRepository.getAllByEventStudioId(albumId).size() == 1) {
            eventImagesStudio.setCoverImage(true);
            eventStudio.setCoverImage(eventImagesStudio.getImage());
            eventStudio.setBluer(eventImagesStudio.getBluer());
        }

        eventImagesStudioRepository.save(eventImagesStudio);

        log.info("Event studio images saved !");

        return EventImagesStudioMapper.INSTANCE.map(eventImagesStudio);
    }


    @Override
    public EventStudioImagesResponse patch(Long id, EventStudioImagesRequest eventStudioImagesRequest) throws NoSuchFieldException, IllegalAccessException {
        EventImagesStudio eventImagesStudio = eventImagesStudioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Event studio image with id: %s not found".formatted(id))
        );

        EventStudio eventStudio = eventImagesStudio.getEventStudio();

        boolean cover = eventImagesStudio.isCoverImage();

        String previousCompImage = eventImagesStudio.getImage();
        String previousOrigImage = eventImagesStudio.getOriginalImage();

        eventImagesStudio.setEventStudio(eventStudio);

        Patch.patchWithMediaFields(id, eventStudioImagesRequest, eventImagesStudio, DIRNAME, SERVER);

        if (previousCompImage != null & eventStudioImagesRequest.getImage() != null) {
            imageService.delete(previousCompImage,eventImagesStudioRepository.findAllByImagePath());
            imageService.delete(previousOrigImage,eventImagesStudioRepository.findAllByOriginalImagePath());
        }

        if (eventStudioImagesRequest.isCoverImage()) {
            eventImagesStudioRepository.updateAllByCoverImage(eventStudio,id);
            eventStudio.setCoverImage(eventImagesStudio.getImage());
            eventStudio.setBluer(eventImagesStudio.getBluer());
        } else if (cover) {
            eventImagesStudioRepository.updateByCoverImageModifying(eventStudio);
            eventStudio.setCoverImage(eventImagesStudioRepository.getAllByEventStudioId(eventStudio.getId()).get(0).getImage());
            eventStudio.setBluer(eventImagesStudioRepository.getAllByEventStudioId(eventStudio.getId()).get(0).getBluer());

        }

        eventImagesStudioRepository.save(eventImagesStudio);

        if (eventImagesStudioRepository.getAllByEventStudioId(eventStudio.getId()).get(0).getId().equals(id)){
            eventImagesStudio.setCoverImage(true);
        }

        return EventImagesStudioMapper.INSTANCE.map(eventImagesStudio);
    }

    @Override
    public List<EventStudioImagesResponse> getAllByAlbumId(Long id) {
        return eventImagesStudioRepository.getAllByEventStudioId(id);
    }

    @Override
    public EventStudioImagesResponse delete(Long id) {
        EventImagesStudio eventImagesStudio = eventImagesStudioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Event studio image with id : %s not found".formatted(id))
        );

        EventStudio eventStudio = eventImagesStudio.getEventStudio();


        eventImagesStudio.setEventStudio(eventStudio);


        if (new File(eventImagesStudio.getImagePath()).exists() && new File(eventImagesStudio.getOriginalImagePath()).exists()) {
            imageService.delete(eventImagesStudio.getImagePath(),eventImagesStudioRepository.findAllByImagePath());
            imageService.delete(eventImagesStudio.getOriginalImagePath(),eventImagesStudioRepository.findAllByOriginalImagePath());
        } else if (new File(eventImagesStudio.getImagePath()).exists()) {
            imageService.delete(eventImagesStudio.getImagePath(),eventImagesStudioRepository.findAllByImagePath());
        } else if (new File(eventImagesStudio.getOriginalImagePath()).exists()) {
            imageService.delete(eventImagesStudio.getOriginalImagePath(),eventImagesStudioRepository.findAllByOriginalImagePath());
        }

        eventImagesStudioRepository.deleteById(id);

        if (eventImagesStudio.isCoverImage() && !eventImagesStudioRepository.getAllByEventStudioId(eventStudio.getId()).isEmpty()){
            eventImagesStudioRepository.updateByCoverImageModifying(eventStudio);
            eventStudio.setCoverImage(eventImagesStudioRepository.getAllByEventStudioId(eventStudio.getId()).get(0).getImage());
            eventStudio.setBluer(eventImagesStudioRepository.getAllByEventStudioId(eventStudio.getId()).get(0).getBluer());
        } else if (eventImagesStudio.isCoverImage()){
            eventStudio.setCoverImage(null);
            eventStudio.setBluer(null);
        }

        log.info("Event Image Studio is deleted");

        return EventImagesStudioMapper.INSTANCE.map(eventImagesStudio);
    }

    @Override
    public EventStudioImagesResponse getById(Long id) {
        return eventImagesStudioRepository.getEventImagesStudioById(id).orElseThrow(() ->
                new NotFoundException("Event studio image with id : %s not found".formatted(id)));
    }


}
