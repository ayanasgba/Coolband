package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import kg.geeks.coolband.dto.request.PartnersBandRequest;
import kg.geeks.coolband.dto.response.PartnersBandResponse;
import kg.geeks.coolband.entities.PartnersBand;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.PartnersBandRepository;
import kg.geeks.coolband.services.PartnersBandService;
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
@RequestMapping("/api/partners_band")
@Tag(name = "Partners Band")
public class PartnersBandController {

    private final PartnersBandService partnersBandService;
    private final PartnersBandRepository partnersBandRepository;

    @GetMapping
    @PermitAll
    List<PartnersBandResponse> getAll() {
        return partnersBandService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    PartnersBandResponse save(@Valid PartnersBandRequest partnersBandRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("All fields must not be null");
        }
        return partnersBandService.save(partnersBandRequest);
    }

    @GetMapping("/{id}")
    @PermitAll
    PartnersBandResponse getById(@PathVariable("id") Long id) {
        return partnersBandService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    PartnersBandResponse deleteById(@PathVariable("id") Long id) {
        return partnersBandService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    PartnersBandResponse updatePartnersBand(@PathVariable("id") Long id,
                                            PartnersBandRequest partnersBandRequest) throws NoSuchFieldException, IllegalAccessException {
        return partnersBandService.patch(id, partnersBandRequest);
    }

    @GetMapping("/image/{id}")
    @Cacheable("images")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getImageById(@PathVariable("id") Long id) throws IOException {

        PartnersBand partnersBand = partnersBandRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id: %s not found".formatted(id))
        );
        return Media.getImageByteArrayResourceResponseEntity(partnersBand, false);
    }
}
