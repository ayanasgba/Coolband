package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.HeroBandResponse;
import kg.geeks.coolband.entities.HeroBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroBandRepository extends JpaRepository<HeroBand,Long>{

    @Query("select new kg.geeks.coolband.dto.response.HeroBandResponse(e.id,e.video,e.orientation) from HeroBand e order by e.id")
    List<HeroBandResponse> getAll();

    @Query("select e.videoPath from HeroBand e order by e.id")
    List<String> findAllByVideoPath();

    Optional<HeroBandResponse> getHeroBandsById(Long id);
}
