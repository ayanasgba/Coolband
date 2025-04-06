package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import kg.geeks.coolband.dto.request.AboutUsBandRequest;
import kg.geeks.coolband.dto.response.AboutUsBandResponse;
import kg.geeks.coolband.entities.AboutUsBand;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.AboutUsBandRepository;
import kg.geeks.coolband.services.AboutUsBandService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/about_us_band")
@Tag(name = "About Us Band")
public class AboutUsBandController {

    private final AboutUsBandService aboutUsBandService;

    private final AboutUsBandRepository aboutUsBandRepository;

    @GetMapping()
    @PermitAll
    AboutUsBandResponse getById() {
        return aboutUsBandService.get();
    }

    @PatchMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Update about us band with id", description = "Only admin can update")
    AboutUsBandResponse updateAboutUsBand(AboutUsBandRequest aboutUsBandRequest) throws IllegalAccessException, NoSuchFieldException {
        return aboutUsBandService.patch(aboutUsBandRequest);
    }

    @GetMapping("/image/{id}")
    @Cacheable("images")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getImageById(@PathVariable("id") Long id) throws Exception {
        AboutUsBand aboutUsBand = aboutUsBandRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id: %s not found".formatted(id))
        );
        return Media.getImageByteArrayResourceResponseEntity(aboutUsBand,false);
    }
}
