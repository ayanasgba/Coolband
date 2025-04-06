package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import kg.geeks.coolband.dto.request.CollaborationsBandRequest;
import kg.geeks.coolband.dto.response.CollaborationsBandResponse;
import kg.geeks.coolband.entities.CollaborationsBand;
import kg.geeks.coolband.entities.TeamBand;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.CollaborationsBandRepository;
import kg.geeks.coolband.services.CollaborationsBandService;
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
@RequestMapping("/api/collaborations_band")
@Tag(name = "Collaborations Band")
public class CollaborationsBandController {

    private final CollaborationsBandService collaborationsBandService;

    private final CollaborationsBandRepository collaborationsBandRepository;

    @GetMapping
    @PermitAll
    @Operation(summary = "Get all collaborations band", description = "All users can this")
    List<CollaborationsBandResponse> getAll() {
        return collaborationsBandService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Save collaborations", description = "Only admin can save")
    CollaborationsBandResponse save(@Valid CollaborationsBandRequest collaborationsBandRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException("All fields must not be null");
        }
        return collaborationsBandService.save(collaborationsBandRequest);
    }

    @GetMapping("/{id}")
    @PermitAll
    @Operation(summary = "Get collaborations with id", description = "All users can this")
    CollaborationsBandResponse getById(@PathVariable("id") Long id) {
        return collaborationsBandService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Delete collaboration with id", description = "Only admin can delete")
    CollaborationsBandResponse deleteById(@PathVariable("id") Long id) {
        return collaborationsBandService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Update collaboration update with id", description = "Only admin can update")
    CollaborationsBandResponse updateCollaborationsBand(@PathVariable("id") Long id,
                                             CollaborationsBandRequest collaborationsBandRequest) throws NoSuchFieldException, IllegalAccessException {
        return collaborationsBandService.patch(id, collaborationsBandRequest);
    }

    @GetMapping("/image/{id}")
    @Cacheable("images")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getImageById(@PathVariable("id") Long id) throws IOException {

        CollaborationsBand collaborationsBand = collaborationsBandRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id: %s not found".formatted(id))
        );
        return Media.getImageByteArrayResourceResponseEntity(collaborationsBand,false);
    }
}