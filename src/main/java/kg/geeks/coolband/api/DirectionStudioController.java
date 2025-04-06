package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import kg.geeks.coolband.dto.request.DirectionStudioRequest;
import kg.geeks.coolband.dto.response.DirectionStudioResponse;
import kg.geeks.coolband.entities.DirectionStudio;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.DirectionStudioRepository;
import kg.geeks.coolband.services.DirectionStudioService;
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
@RequiredArgsConstructor
@RequestMapping("/api/direction")
@Tag(name="Direction Studio")
public class DirectionStudioController {

    private final DirectionStudioService directionStudioService;

    private final DirectionStudioRepository directionStudioRepository;

    @GetMapping
    @PermitAll
    List<DirectionStudioResponse> getAll() {
        return directionStudioService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    DirectionStudioResponse save(@Valid DirectionStudioRequest directionStudioRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException("All fields must not be null");
        }
        return directionStudioService.save(directionStudioRequest);
    }

    @GetMapping("/{id}")
    @PermitAll
    DirectionStudioResponse getById(@PathVariable("id") Long id) {
        return directionStudioService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    DirectionStudioResponse deleteById(@PathVariable("id") Long id) {
        return directionStudioService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    DirectionStudioResponse updateEventBand(@PathVariable("id") Long id, DirectionStudioRequest directionStudioRequest) throws NoSuchFieldException, IllegalAccessException {
        return directionStudioService.patch(id, directionStudioRequest);
    }

    @GetMapping("/image/{id}")
    @Cacheable("images")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getImageById(@PathVariable("id") Long id) throws IOException {

        DirectionStudio directionStudio = directionStudioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id: %s not found".formatted(id))
        );
        return Media.getImageByteArrayResourceResponseEntity(directionStudio,false);
    }

}
