package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.geeks.coolband.dto.request.EventBandRequest;
import kg.geeks.coolband.dto.response.EventBandResponse;
import jakarta.annotation.security.PermitAll;
import kg.geeks.coolband.services.Impl.EventBandServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event_band")
@Tag(name = "Event Band")
public class EventBandController {

    private final EventBandServiceImpl eventBandService;

    @GetMapping
    @PermitAll
    @Operation(summary = "Get all event band", description = "All users can this")
    List<EventBandResponse> getAll(){
        return eventBandService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Save event band", description = "Only admin can save")
    EventBandResponse save(@Valid @RequestBody EventBandRequest eventBandRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException("All fields must not be null");
        }
        return eventBandService.save(eventBandRequest);
    }

    @GetMapping("/{id}")
    @PermitAll
    @Operation(summary = "Get event band by id", description = "All users can this")
    EventBandResponse getById(@PathVariable("id") Long eventBandId){
        return eventBandService.getById(eventBandId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Delete event band with id", description = "Only admin can delete")
    EventBandResponse deleteById(@PathVariable("id") Long eventBandId){
        return eventBandService.deleteById(eventBandId);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Update event band with id", description = "Only admin can update")
    EventBandResponse updateEventBand(@PathVariable("id") Long eventBandId,
                                   EventBandRequest eventBandRequest) throws NoSuchFieldException, IllegalAccessException {
        return eventBandService.patch(eventBandId,eventBandRequest);
    }
}