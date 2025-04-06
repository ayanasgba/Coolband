package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import kg.geeks.coolband.dto.request.StStudentReviewsRequest;
import kg.geeks.coolband.dto.response.StStudentReviewsResponse;
import kg.geeks.coolband.entities.StStudentReviews;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.StStudentReviewsRepository;
import kg.geeks.coolband.services.StStudentReviewsService;
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
@RequestMapping("/api/st_student_reviews")
@RequiredArgsConstructor
@Tag(name = "Student Reviews Studio")
public class StStudentReviewsController {

    private final StStudentReviewsService stStudentReviewsService;

    private final StStudentReviewsRepository stStudentReviewsRepository;

    @GetMapping()
    @PermitAll
    List<StStudentReviewsResponse> getAll() {
        return stStudentReviewsService.getAll();
    }

    @GetMapping("/{id}")
    @PermitAll
    StStudentReviewsResponse getById(@PathVariable("id") Long id) {
        return stStudentReviewsService.getById(id);
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    StStudentReviewsResponse createStStudentReviews(@Valid StStudentReviewsRequest stStudentReviewsRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException("All fields must not be null");
        }
        return stStudentReviewsService.save(stStudentReviewsRequest);
    }


    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    StStudentReviewsResponse updateReviews(@PathVariable("id") Long stStudentReviewsId, StStudentReviewsRequest stStudentReviewsRequest) throws NoSuchFieldException, IllegalAccessException {
        return stStudentReviewsService.patch(stStudentReviewsId, stStudentReviewsRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    StStudentReviewsResponse deleteStREviews(@PathVariable Long id) {
        return stStudentReviewsService.deleteById(id);
    }

    @GetMapping("/image/{id}")
    @Cacheable("images")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getImageById(@PathVariable("id") Long id) throws IOException {

        StStudentReviews stStudentReviews = stStudentReviewsRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id: %s not found".formatted(id))
        );
        return Media.getImageByteArrayResourceResponseEntity(stStudentReviews, false);
    }

}
