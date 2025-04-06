package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.dto.request.EventBandRequest;
import kg.geeks.coolband.dto.response.EventBandResponse;
import kg.geeks.coolband.entities.EventBand;
import kg.geeks.coolband.exceptions.BadCredentialException;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.EventBandMapper;
import kg.geeks.coolband.repository.EventBandRepository;
import kg.geeks.coolband.services.EventBandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EventBandServiceImpl implements EventBandService {

    private final EventBandRepository eventBandRepository;

    @Override
    public EventBandResponse patch(Long id, EventBandRequest eventBandRequest) throws NoSuchFieldException, IllegalAccessException {
        EventBand eventBand = eventBandRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Event Band with id: %s not found".formatted(id))
        );

        Patch.patchWithNonMediaFields(eventBandRequest, eventBand);

        eventBandRepository.save(eventBand);

        return EventBandMapper.INSTANCE.map(eventBand);
    }

    @Override
    public EventBandResponse deleteById(Long eventBandId) {
        EventBandResponse eventBandResponse = eventBandRepository.getEventBandById(eventBandId).orElseThrow(
                () -> new BadCredentialException("Event Band with id : %s doesn't exists".formatted(eventBandId))
        );
        eventBandRepository.deleteById(eventBandId);
        return eventBandResponse;
    }

    @Override
    public EventBandResponse getById(Long eventBandId) {
        return eventBandRepository.getEventBandById(eventBandId).orElseThrow(() ->
                new NotFoundException("Event Band with id : %s not found".formatted(eventBandId)));
    }

    @Override
    public EventBandResponse save(EventBandRequest eventBandRequest) {
        EventBand eventBand = EventBandMapper.INSTANCE.mapRequestToResponse(eventBandRequest);
        eventBandRepository.save(eventBand);
        return EventBandMapper.INSTANCE.map(eventBand);
    }

    @Override
    public List<EventBandResponse> getAll() {
        return eventBandRepository.getAll();
    }

}
