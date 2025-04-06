package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.CollaborationsBandResponse;
import kg.geeks.coolband.entities.CollaborationsBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollaborationsBandRepository extends JpaRepository<CollaborationsBand, Long>{
    @Query("select new kg.geeks.coolband.dto.response.CollaborationsBandResponse(e.id, e.name, e.image,e.bluer,e.orientation) from CollaborationsBand e order by e.id")
    List<CollaborationsBandResponse> getAll();
    
    @Query("select e.imagePath from CollaborationsBand e order by e.id")
    List<String> findAllByImagePath();

    Optional<CollaborationsBandResponse> getCollaborationsBandById(Long id);
}
