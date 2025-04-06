package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import kg.geeks.coolband.dto.request.TeamBandRequest;
import kg.geeks.coolband.dto.response.TeamBandResponse;
import kg.geeks.coolband.entities.TeamBand;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.TeamBandRepository;
import kg.geeks.coolband.services.Impl.TeamBandServiceImpl;
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
@RequestMapping("/api/team_band")
@Tag(name = "Team Band")
public class TeamBandController {

    private final TeamBandServiceImpl teamBandService;

    private final TeamBandRepository teamBandRepository;

    @GetMapping
    @PermitAll
    List<TeamBandResponse> getAll() {
        return teamBandService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    TeamBandResponse save(@Valid TeamBandRequest teamBandRequest, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("All fields must not be null");
        }

        return teamBandService.save(teamBandRequest);
    }

    @GetMapping("/{id}")
    @PermitAll
    TeamBandResponse getById(@PathVariable("id") Long id) {
        return teamBandService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    TeamBandResponse deleteById(@PathVariable("id") Long id) {
        return teamBandService.deleteById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    TeamBandResponse updateTeamBandBand(@PathVariable("id") Long id, TeamBandRequest teamBandRequest) throws NoSuchFieldException, IllegalAccessException {
        return teamBandService.patch(id, teamBandRequest);
    }

    @GetMapping("/image/{id}")
    @Cacheable("images")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getImageById(@PathVariable("id") Long id) throws IOException {

        TeamBand teamBand = teamBandRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id: %s not found".formatted(id))
        );
        return Media.getImageByteArrayResourceResponseEntity(teamBand, false);
    }

    @GetMapping("/video/{id}")
    @Cacheable("videos")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getVideoById(@PathVariable("id") Long id) throws IOException {

        TeamBand teamBand = teamBandRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Video with id: %s not found".formatted(id))
        );
        return Media.getVideoByteArrayResourceResponseEntity(teamBand);
    }

}
