package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import kg.geeks.coolband.dto.request.StTeachersRequest;
import kg.geeks.coolband.dto.response.StTeachersResponse;
import kg.geeks.coolband.entities.StTeachers;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.repository.StTeachersRepository;
import kg.geeks.coolband.services.StTeachersService;
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
@RequestMapping("/api/st_teachers")
@RequiredArgsConstructor
@Tag(name = "Teachers Studio")
public class StTeachersController {

    private final StTeachersService stTeachersService;

    private final StTeachersRepository stTeachersRepository;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    StTeachersResponse createStTeachers(@Valid StTeachersRequest stTeachersRequest, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException("All fields must not be null");
        }
        return stTeachersService.save(stTeachersRequest);
    }

    @GetMapping
    @PermitAll
    List<StTeachersResponse> getAll() {
        return stTeachersService.getAll();
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER') ")
    StTeachersResponse updateStTeachers(@PathVariable("id") Long stTeacherId, StTeachersRequest stTeachersRequest) throws NoSuchFieldException, IllegalAccessException {
        return stTeachersService.patch(stTeacherId, stTeachersRequest);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    StTeachersResponse deleteStTeachers(@PathVariable("id") Long id) {
        return stTeachersService.deleteById(id);
    }


    @GetMapping("/{id}")
    @PermitAll
    StTeachersResponse getStTeacherById(@PathVariable("id") Long id) {
        return stTeachersService.getById(id);
    }

    @GetMapping("/image/{id}")
    @Cacheable("images")
    @PermitAll
    @Hidden
    public ResponseEntity<ByteArrayResource> getImageById(@PathVariable("id") Long id) throws IOException {

        StTeachers stTeachers = stTeachersRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Image with id: %s not found".formatted(id))
        );
        return Media.getImageByteArrayResourceResponseEntity(stTeachers, false);
    }
}
