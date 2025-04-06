package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.dto.request.EventStudioRequest;
import kg.geeks.coolband.dto.response.EventStudioResponse;
import kg.geeks.coolband.entities.EventStudio;
import kg.geeks.coolband.exceptions.BadCredentialException;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.EventStudioMapper;
import kg.geeks.coolband.repository.EventStudioRepository;
import kg.geeks.coolband.services.EventStudioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EventStudioServiceImpl implements EventStudioService {

    private final EventStudioRepository eventStudioRepository;

    @Override
    public EventStudioResponse save(EventStudioRequest eventStudioRequest) {
        EventStudio eventStudio = EventStudioMapper.INSTANCE.mapRequestToResponse(eventStudioRequest);
        eventStudioRepository.save(eventStudio);
        return EventStudioMapper.INSTANCE.map(eventStudio);
    }

    @Override
    public EventStudioResponse getById(Long id) {
        return eventStudioRepository.getEventStudioById(id).orElseThrow(() ->
                new NotFoundException("Event Studio with id: %s not found".formatted(id)));
    }


    @Override
    public List<EventStudioResponse> getAll() {
        return eventStudioRepository.getAll();

    }

    @Override
    public EventStudioResponse patch(Long id, EventStudioRequest eventStudioRequest) throws NoSuchFieldException, IllegalAccessException {
        EventStudio eventStudio = eventStudioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Event Studio with id: %s not found".formatted(id))
        );

        Patch.patchWithNonMediaFields(eventStudioRequest,eventStudio);

        eventStudioRepository.save(eventStudio);

        log.info("Event Studio updated!");

        return EventStudioMapper.INSTANCE.map(eventStudio);


    }

    @Override
    public EventStudioResponse deleteById(Long id) {
        EventStudioResponse eventStudioResponse = eventStudioRepository.getEventStudioById(id).orElseThrow(
                () -> new BadCredentialException("Event Studio with id : %s doesn't exists".formatted(id))
        );
        eventStudioRepository.deleteById(id);
        log.info("Event Studio deleted !");
        return eventStudioResponse;
    }

}
