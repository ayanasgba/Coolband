package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import kg.geeks.coolband.dto.request.HeroStudioRequest;
import kg.geeks.coolband.dto.response.HeroStudioResponse;
import kg.geeks.coolband.entities.HeroStudio;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.HeroStudioRepository;
import kg.geeks.coolband.services.HeroStudioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/hero_studio")
@Tag(name = "Hero Studio")
public class HeroStudioController {

    private final HeroStudioService heroStudioService;

    private final HeroStudioRepository heroStudioRepository;


    @GetMapping()
    @PermitAll
    HeroStudioResponse get() {
        return heroStudioService.get();
    }

    @PatchMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Update hero studio with id", description = "Only admin can update")
    HeroStudioResponse update(HeroStudioRequest heroStudioRequest) throws IllegalAccessException, NoSuchFieldException {
        return heroStudioService.patch(heroStudioRequest);
    }

    @GetMapping("/video/{id}")
    @Cacheable("videos")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getVideoById(@PathVariable("id") Long id) throws Exception {
        HeroStudio heroStudio = heroStudioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Video with id: %s not found".formatted(id))
        );
        return Media.getVideoByteArrayResourceResponseEntity(heroStudio);
    }
}
