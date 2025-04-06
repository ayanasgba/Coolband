package kg.geeks.coolband.repository;

import kg.geeks.coolband.entities.HeroStudio;
import kg.geeks.coolband.dto.response.HeroStudioResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroStudioRepository extends JpaRepository<HeroStudio, Long> {

    @Query("select new kg.geeks.coolband.dto.response.HeroStudioResponse(e.id, e.title, e.text, e.video, e.orientation) from HeroStudio e order by e.id")
    List<HeroStudioResponse> getAll();


    @Query("select e.videoPath from HeroStudio e order by e.id")
    List<String> findAllByVideoPath();

    Optional<HeroStudioResponse> getHeroStudioById(Long id);

}
