package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.EventStudioResponse;
import kg.geeks.coolband.entities.EventStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventStudioRepository extends JpaRepository<EventStudio, Long> {

    @Query("select new kg.geeks.coolband.dto.response.EventStudioResponse(e.id,e.date,e.name,e.coverImage,e.bluer,e.location) from EventStudio e order by e.id")
    List<EventStudioResponse> getAll();

    Optional<EventStudioResponse> getEventStudioById(Long id);

}
