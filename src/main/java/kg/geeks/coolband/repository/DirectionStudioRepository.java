package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.DirectionStudioResponse;
import kg.geeks.coolband.entities.DirectionStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DirectionStudioRepository extends JpaRepository<DirectionStudio, Long> {


    @Query("select new kg.geeks.coolband.dto.response.DirectionStudioResponse(e.id, e.name, e.image, e.bluer,e.orientation) from DirectionStudio e order by e.id")
    List<DirectionStudioResponse> getAll();

    @Query("select e.imagePath from DirectionStudio e order by e.id")
    List<String> findAllByImagePath();

    Optional<DirectionStudioResponse> findDirectionStudioById(Long id);

}
