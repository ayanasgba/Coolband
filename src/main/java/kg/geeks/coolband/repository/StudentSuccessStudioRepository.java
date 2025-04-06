package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.StudentSuccessStudioResponse;
import kg.geeks.coolband.entities.StudentSuccessStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentSuccessStudioRepository extends JpaRepository<StudentSuccessStudio,Long> {

    @Query("select new kg.geeks.coolband.dto.response.StudentSuccessStudioResponse(s.id,s.url) from StudentSuccessStudio s order by s.id")
    List<StudentSuccessStudioResponse> getAll();

    Optional<StudentSuccessStudioResponse> getStSuStById(Long id);
}
