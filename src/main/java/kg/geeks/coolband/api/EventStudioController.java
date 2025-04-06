package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import kg.geeks.coolband.dto.request.EventStudioRequest;
import kg.geeks.coolband.dto.response.EventStudioResponse;
import kg.geeks.coolband.services.Impl.EventStudioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/event_studio")
@RequiredArgsConstructor
@Tag(name = "Event Studio")
public class EventStudioController {

    private final EventStudioServiceImpl eventStudioService;

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Save event studio",
            description = "Only admin can save")
    @PostMapping()
    public EventStudioResponse createEvent(@Valid @RequestBody EventStudioRequest eventStudioRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException("All fields must not be null");
        }
        return eventStudioService.save(eventStudioRequest);
    }

    @PermitAll
    @GetMapping()
    public List<EventStudioResponse> getAll() {
        return eventStudioService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Update event studio",
            description = "Only admin can updated")
    @PatchMapping("/{id}")
    public EventStudioResponse updateEventStudio(@PathVariable("id") Long id, EventStudioRequest eventStudioRequest) throws NoSuchFieldException, IllegalAccessException {
        return eventStudioService.patch(id,eventStudioRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Delete event studio",
            description = "Only admin can deleted")
    @DeleteMapping("/{id}")
    public EventStudioResponse deleteEventStudio(@PathVariable("id") Long id) {
        return eventStudioService.deleteById(id);
    }

    @GetMapping("/{id}")
    @PermitAll
    public EventStudioResponse getById(@PathVariable("id") Long id){
        return eventStudioService.getById(id);
    }
}
