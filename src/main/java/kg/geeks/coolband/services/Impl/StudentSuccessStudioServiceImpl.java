package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.dto.request.StudentSuccessStudioRequest;
import kg.geeks.coolband.dto.response.StudentSuccessStudioResponse;
import kg.geeks.coolband.entities.StudentSuccessStudio;
import kg.geeks.coolband.exceptions.BadCredentialException;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.StudentSuccessStudioMapper;
import kg.geeks.coolband.repository.StudentSuccessStudioRepository;
import kg.geeks.coolband.services.StudentSuccessStudioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentSuccessStudioServiceImpl implements StudentSuccessStudioService {

    private final StudentSuccessStudioRepository studentSuccessStudioRepository;

    @Override
    public List<StudentSuccessStudioResponse> getAll() {
        return studentSuccessStudioRepository.getAll();
    }

    @Override
    public StudentSuccessStudioResponse save(StudentSuccessStudioRequest studentSuccessStudioRequest) {
        StudentSuccessStudio studentSuccessStudio = StudentSuccessStudioMapper.INSTANCE.mapRequestToResponse(studentSuccessStudioRequest);
        studentSuccessStudioRepository.save(studentSuccessStudio);
        log.info("Student success studio is saved");
        return StudentSuccessStudioMapper.INSTANCE.map(studentSuccessStudio);
    }

    @Override
    public StudentSuccessStudioResponse getById(Long id) {
        return studentSuccessStudioRepository.getStSuStById(id).orElseThrow(() ->
                new NotFoundException("Student success studio with id: %s not found".formatted(id)));
    }

    @Override
    public StudentSuccessStudioResponse deleteById(Long id) {
        StudentSuccessStudioResponse studentSuccessStudioResponse = studentSuccessStudioRepository.getStSuStById(id)
                .orElseThrow(() -> new BadCredentialException("Student success studio with id : %s doesn't exists".formatted(id)));
        studentSuccessStudioRepository.deleteById(id);
        return studentSuccessStudioResponse;
    }

    @Override
    public StudentSuccessStudioResponse patch(Long id, StudentSuccessStudioRequest studentSuccessStudioRequest) throws NoSuchFieldException, IllegalAccessException {
        StudentSuccessStudio studentSuccessStudio = studentSuccessStudioRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Student success studio with id: %s not found".formatted(id))
        );

        Patch.patchWithNonMediaFields(studentSuccessStudioRequest, studentSuccessStudio);

        studentSuccessStudioRepository.save(studentSuccessStudio);

        log.info("Student success is updated");

        return StudentSuccessStudioMapper.INSTANCE.map(studentSuccessStudio);

    }
}
