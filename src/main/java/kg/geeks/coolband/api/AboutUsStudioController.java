package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import kg.geeks.coolband.dto.request.AboutUsStudioRequest;
import kg.geeks.coolband.dto.response.AboutUsStudioResponse;
import kg.geeks.coolband.entities.AboutUsStudio;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.AboutUsStudioRepository;
import kg.geeks.coolband.services.AboutUsStudioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/about_us_studio")
@Tag(name = "About Us Studio")
public class AboutUsStudioController {

    private final AboutUsStudioService aboutUsStudioService;

    private final AboutUsStudioRepository aboutUsStudioRepository;


    @GetMapping()
    @PermitAll
    AboutUsStudioResponse getById() {
        return aboutUsStudioService.get();
    }


    @PatchMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Update about us studio with id", description = "Only admin can update")
    AboutUsStudioResponse updateEventBand(AboutUsStudioRequest aboutUsStudioRequest) throws IllegalAccessException, NoSuchFieldException {
        return aboutUsStudioService.patch(aboutUsStudioRequest);
    }

    @GetMapping("/image/{id}")
    @Cacheable("images")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getImageById(@PathVariable("id") Long id) throws IOException {

        AboutUsStudio aboutUsStudio = aboutUsStudioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id: %s not found".formatted(id))
        );

        return Media.getImageByteArrayResourceResponseEntity(aboutUsStudio,false);
    }
}