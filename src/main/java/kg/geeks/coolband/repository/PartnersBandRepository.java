package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.PartnersBandResponse;
import kg.geeks.coolband.entities.PartnersBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartnersBandRepository extends JpaRepository<PartnersBand, Long> {
    @Query("select new kg.geeks.coolband.dto.response.PartnersBandResponse(e.id, e.image,e.bluer, e.url) from PartnersBand e order by e.id")
    List<PartnersBandResponse> getAll();


    @Query("select e.imagePath from PartnersBand e order by e.id")
    List<String> findAllByImagePath();

    Optional<PartnersBandResponse> findPartnersBandById(Long id);
}
