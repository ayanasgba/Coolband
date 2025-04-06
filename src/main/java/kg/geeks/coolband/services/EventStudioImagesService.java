package kg.geeks.coolband.services;


import kg.geeks.coolband.dto.request.EventStudioImagesRequest;
import kg.geeks.coolband.dto.response.EventStudioImagesResponse;

import java.util.List;

public interface EventStudioImagesService {

    List<EventStudioImagesResponse> getAll();

    EventStudioImagesResponse save(EventStudioImagesRequest eventStudioImagesRequest);

    EventStudioImagesResponse patch(Long id, EventStudioImagesRequest eventStudioImagesRequest) throws NoSuchFieldException, IllegalAccessException;

    List<EventStudioImagesResponse> getAllByAlbumId(Long id);

    EventStudioImagesResponse delete(Long id);

    EventStudioImagesResponse getById(Long id);
}
