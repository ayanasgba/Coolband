package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import kg.geeks.coolband.dto.request.EventStudioImagesRequest;
import kg.geeks.coolband.dto.response.EventStudioImagesResponse;
import kg.geeks.coolband.entities.EventImagesStudio;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.EventImagesStudioRepository;
import kg.geeks.coolband.services.EventStudioImagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/event_studio_images")
@RequiredArgsConstructor
@Tag(name = "Event Studio Images")
public class EventStudioImagesController {

    private final EventStudioImagesService eventStudioImagesService;

    private final EventImagesStudioRepository eventImagesStudioRepository;


    @GetMapping
    @PermitAll
    List<EventStudioImagesResponse> getAll() {
        return eventStudioImagesService.getAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Save event studio image",
            description = "Only admin can save")
    EventStudioImagesResponse save(@Valid EventStudioImagesRequest eventStudioImagesRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("All fields must not be null");
        }
        return eventStudioImagesService.save(eventStudioImagesRequest);
    }

    @GetMapping("/{id}")
    @PermitAll
    EventStudioImagesResponse getById(@PathVariable Long id) {
        return eventStudioImagesService.getById(id);
    }

    @GetMapping("/album/{albumId}")
    @PermitAll
    List<EventStudioImagesResponse> getByAlbumId(@PathVariable("albumId") Long id) {
        return eventStudioImagesService.getAllByAlbumId(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @PatchMapping("/{id}")
    @Operation(summary = "Update event studio image",
            description = "Only admin can updated")
    EventStudioImagesResponse patch(@PathVariable("id") Long id,
                                    EventStudioImagesRequest eventStudioImagesRequest) throws NoSuchFieldException, IllegalAccessException {
        return eventStudioImagesService.patch(id, eventStudioImagesRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Delete event studio image",
            description = "Only admin can deleted")
    EventStudioImagesResponse delete(@PathVariable Long id) {
        return eventStudioImagesService.delete(id);
    }

    @GetMapping("/compImage/{id}")
    @Cacheable("images")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getImageById(@PathVariable("id") Long id) throws IOException {
        EventImagesStudio eventImagesStudio = eventImagesStudioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id: %s not found".formatted(id))
        );

        return Media.getImageByteArrayResourceResponseEntity(eventImagesStudio, false);
    }

    @GetMapping("/origImage/{id}")
    @Cacheable("images")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getOriginalImageById(@PathVariable("id") Long id) throws IOException {
        EventImagesStudio eventImagesStudio = eventImagesStudioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id: %s not found".formatted(id))
        );

        return Media.getImageByteArrayResourceResponseEntity(eventImagesStudio, true);
    }
}
