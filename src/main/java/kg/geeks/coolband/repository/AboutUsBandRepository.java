package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.AboutUsBandResponse;
import kg.geeks.coolband.entities.AboutUsBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AboutUsBandRepository extends JpaRepository<AboutUsBand, Long> {

    @Query("select new kg.geeks.coolband.dto.response.AboutUsBandResponse(e.id, e.image, e.orientation, e.bluer) from AboutUsBand e order by e.id")
    List<AboutUsBandResponse> getAll();

    @Query("select e.imagePath from AboutUsBand e order by e.id")
    List<String> findAllByImagePath();


    Optional<AboutUsBandResponse> getAboutUsBandById(Long id);
}

