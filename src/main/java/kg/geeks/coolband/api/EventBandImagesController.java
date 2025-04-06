package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import kg.geeks.coolband.dto.request.EventBandImagesRequest;
import kg.geeks.coolband.dto.response.EventBandImagesResponse;
import kg.geeks.coolband.entities.EventImagesBand;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.EventImagesBandRepository;
import kg.geeks.coolband.services.EventBandImagesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/event_band_images")
@Tag(name = "Event Band Images")
public class EventBandImagesController {

    private final EventBandImagesService eventBandImagesService;

    private final EventImagesBandRepository eventImagesBandRepository;

    @GetMapping
    @PermitAll
    List<EventBandImagesResponse> getAll() {
        return eventBandImagesService.getAll();
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    EventBandImagesResponse save(@Valid EventBandImagesRequest eventBandImagesRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("All fields must not be null");
        }
        return eventBandImagesService.save(eventBandImagesRequest);
    }

    @GetMapping("/{id}")
    @PermitAll
    EventBandImagesResponse getById(@PathVariable("id") Long eventImageId) {
        return eventBandImagesService.getById(eventImageId);
    }

    @GetMapping("/album/{albumId}")
    @PermitAll
    List<EventBandImagesResponse> getByAlbumId(@PathVariable("albumId") Long id) {
        return eventBandImagesService.getAllByAlbumId(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    EventBandImagesResponse patch(@PathVariable("id") Long eventImageId, EventBandImagesRequest eventBandImagesRequest) throws NoSuchFieldException, IllegalAccessException {
        return eventBandImagesService.patch(eventImageId, eventBandImagesRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    EventBandImagesResponse delete(@PathVariable("id") Long eventImageId) {
        return eventBandImagesService.deleteById(eventImageId);
    }

    @GetMapping("/compImage/{id}")
    @Cacheable("images")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getImageById(@PathVariable("id") Long id) throws IOException {
        EventImagesBand eventImagesBand = eventImagesBandRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id: %s not found".formatted(id))
        );

        return Media.getImageByteArrayResourceResponseEntity(eventImagesBand, false);
    }

    @GetMapping("/origImage/{id}")
    @Cacheable("images")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getOriginalImageById(@PathVariable("id") Long id) throws IOException {

        EventImagesBand eventImagesBand = eventImagesBandRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id: %s not found".formatted(id))
        );

        return Media.getImageByteArrayResourceResponseEntity(eventImagesBand, true);
    }
}
