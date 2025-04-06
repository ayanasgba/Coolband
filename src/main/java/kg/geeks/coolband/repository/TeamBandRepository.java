package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.TeamBandResponse;
import kg.geeks.coolband.entities.TeamBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamBandRepository extends JpaRepository<TeamBand, Long> {
    @Query("select new kg.geeks.coolband.dto.response.TeamBandResponse(e.id, e.name, e.image, e.video, e.instrument, e.orientation,e.bluer) from TeamBand e order by e.id")
    List<TeamBandResponse> getAll();


    @Query("select e.videoPath from TeamBand e order by e.id")
    List<String> findAllByVideoPath();

    @Query("select e.imagePath from TeamBand e order by e.id")
    List<String> findAllByImagePath();

    Optional<TeamBandResponse> getTeamBandById(Long id);
}
