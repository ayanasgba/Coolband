package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import kg.geeks.coolband.dto.request.StudentSuccessStudioRequest;
import kg.geeks.coolband.dto.response.StudentSuccessStudioResponse;
import kg.geeks.coolband.services.StudentSuccessStudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student_success_studio")
@RequiredArgsConstructor
@Tag(name = "Student Success Studio")
public class StudentSuccessStudioController {

    private final StudentSuccessStudioService studentSuccessStudioService;

    @GetMapping
    @PermitAll
    List<StudentSuccessStudioResponse> getAll() {
        return studentSuccessStudioService.getAll();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    StudentSuccessStudioResponse save(@RequestBody @Valid StudentSuccessStudioRequest studentSuccessStudioRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("All fields must not be null");
        }
        return studentSuccessStudioService.save(studentSuccessStudioRequest);
    }

    @GetMapping("/{id}")
    @PermitAll
    StudentSuccessStudioResponse getById(@PathVariable("id") Long id) {
        return studentSuccessStudioService.getById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    StudentSuccessStudioResponse update(@PathVariable("id") Long id,
                                        @RequestBody StudentSuccessStudioRequest studentSuccessStudioRequest) throws NoSuchFieldException, IllegalAccessException {
        return studentSuccessStudioService.patch(id, studentSuccessStudioRequest);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    StudentSuccessStudioResponse deleteById(@PathVariable("id") Long id) {
        return studentSuccessStudioService.deleteById(id);
    }
}
