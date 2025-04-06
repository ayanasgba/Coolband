package kg.geeks.coolband.services;

import kg.geeks.coolband.dto.request.EventBandImagesRequest;
import kg.geeks.coolband.dto.response.EventBandImagesResponse;

import java.util.List;

public interface EventBandImagesService {

    List<EventBandImagesResponse> getAll();

    EventBandImagesResponse save(EventBandImagesRequest eventBandImagesRequest);

    EventBandImagesResponse getById(Long eventImageId);

    List<EventBandImagesResponse> getAllByAlbumId(Long eventImageId);

    EventBandImagesResponse patch(Long eventImageId, EventBandImagesRequest eventBandImagesRequest) throws NoSuchFieldException, IllegalAccessException;

    EventBandImagesResponse deleteById(Long eventImageId);
}
