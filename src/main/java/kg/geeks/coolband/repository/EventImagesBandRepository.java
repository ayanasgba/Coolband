package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.EventBandImagesResponse;
import kg.geeks.coolband.entities.EventBand;
import kg.geeks.coolband.entities.EventImagesBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventImagesBandRepository extends JpaRepository<EventImagesBand,Long> {

    @Query("select new kg.geeks.coolband.dto.response.EventBandImagesResponse(e.id,e.eventBand.id,e.image,e.originalImage,e.orientation,e.isCoverImage,e.bluer) from EventImagesBand e order by e.id")
    List<EventBandImagesResponse> getAll();

    @Query("select new kg.geeks.coolband.dto.response.EventBandImagesResponse(e.id,e.eventBand.id, e.image, e.originalImage, e.orientation, e.isCoverImage, e.bluer) from EventImagesBand e where e.eventBand.id = :albumId order by e.id")
    List<EventBandImagesResponse> getAllByEventBandId(@Param("albumId") Long id);

    @Modifying
    @Query("update EventImagesBand e set e.isCoverImage = false where e.isCoverImage = true and e.eventBand = :eventBand and e.id != :id")
    void updateAllByCoverImage(@Param("eventBand") EventBand eventBand, @Param("id") Long id);

    @Query("select new kg.geeks.coolband.dto.response.EventBandImagesResponse(e.id, e.eventBand.id,e.image, e.originalImage, e.orientation, e.isCoverImage, e.bluer) from EventImagesBand e where e.id = :id")
    Optional<EventBandImagesResponse> getEventImagesBandById(@Param("id") Long eventImageId);

    @Modifying
    @Query("update EventImagesBand e set e.isCoverImage = true where e.id = (select s.id from EventImagesBand s where s.eventBand = :eventBand order by s.id limit 1)")
    void updateByCoverImageModifying(@Param("eventBand") EventBand eventBand);

    @Query("select e.imagePath from EventImagesBand e order by e.id")
    List<String> findAllByImagePath();

    @Query("select e.originalImagePath from EventImagesBand e order by e.id")
    List<String> findAllByOriginalImagePath();


}
