package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.AboutUsStudioResponse;
import kg.geeks.coolband.entities.AboutUsStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AboutUsStudioRepository extends JpaRepository<AboutUsStudio, Long> {

    @Query("select new kg.geeks.coolband.dto.response.AboutUsStudioResponse(e.id, e.image,e.orientation,e.bluer,e.youtubeUrl) from AboutUsStudio e order by e.id")
    List<AboutUsStudioResponse> getAll();


    @Query("select e.imagePath from AboutUsStudio e order by e.id")
    List<String> findAllByImagePath();

    Optional<AboutUsStudioResponse> getAboutUsStudioById(Long id);

}
