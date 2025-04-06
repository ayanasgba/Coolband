package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.EventBandResponse;
import kg.geeks.coolband.entities.EventBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventBandRepository extends JpaRepository<EventBand,Long> {

    @Query("select new kg.geeks.coolband.dto.response.EventBandResponse(e.id,e.coverImage,e.date,e.name,e.bluer) from EventBand e order by e.id")
    List<EventBandResponse> getAll();

    Optional<EventBandResponse> getEventBandById(Long id);

}
