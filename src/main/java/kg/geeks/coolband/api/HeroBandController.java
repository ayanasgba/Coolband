package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import kg.geeks.coolband.dto.request.HeroBandRequest;
import kg.geeks.coolband.dto.response.HeroBandResponse;
import kg.geeks.coolband.entities.HeroBand;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.HeroBandRepository;
import kg.geeks.coolband.services.Impl.HeroBandServiceImpl;
import kg.geeks.coolband.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hero_band")
@Tag(name="Hero Band")
public class  HeroBandController {

    private final HeroBandServiceImpl heroBandService;

    private final HeroBandRepository heroBandRepository;

    private final VideoService videoService;


    @GetMapping()
    @PermitAll
    @Operation(summary = "Get hero band", description = "All users can see this")
    HeroBandResponse getById() {
        return heroBandService.get();
    }


    @PatchMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Update hero band", description = "Only admin can update")
    HeroBandResponse updateVideo(HeroBandRequest heroBandRequest) throws NoSuchFieldException, IllegalAccessException {
        return heroBandService.patch(heroBandRequest);
    }

    @GetMapping("/video/{id}")
    @Cacheable("videos")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getVideoById(@PathVariable("id") Long id) throws IOException {
        HeroBand heroBand = heroBandRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id: %s not found".formatted(id))
        );

        return Media.getVideoByteArrayResourceResponseEntity(heroBand);
    }

    @GetMapping("/mvp")
    @PermitAll
    @Hidden
    public String test (HeroBandRequest heroBandRequest) {
        return videoService.uploadCompressed(heroBandRequest.getVideo(),"aasd");
    }
}
