package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.EventStudioImagesResponse;
import kg.geeks.coolband.entities.EventImagesStudio;
import kg.geeks.coolband.entities.EventStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventImagesStudioRepository extends JpaRepository<EventImagesStudio, Long> {

    @Query("select new kg.geeks.coolband.dto.response.EventStudioImagesResponse(e.id,e.eventStudio.id, e.image, e.originalImage, e.orientation, e.isCoverImage, e.bluer) from EventImagesStudio e order by e.id")
    List<EventStudioImagesResponse> getAll();

    @Query("select new kg.geeks.coolband.dto.response.EventStudioImagesResponse(e.id,e.eventStudio.id, e.image, e.originalImage, e.orientation, e.isCoverImage, e.bluer) from EventImagesStudio e where e.eventStudio.id = :albumId order by e.id")
    List<EventStudioImagesResponse> getAllByEventStudioId(@Param("albumId") Long id);

    @Modifying
    @Query("update EventImagesStudio e set e.isCoverImage = false where e.isCoverImage = true and e.eventStudio = :eventStudio and e.id != :imageId")
    void updateAllByCoverImage(@Param("eventStudio") EventStudio eventStudio, @Param("imageId") Long imageId);

    @Query("select new kg.geeks.coolband.dto.response.EventStudioImagesResponse(e.id, e.eventStudio.id,e.image, e.originalImage, e.orientation, e.isCoverImage, e.bluer) from EventImagesStudio e where e.id = :id")
    Optional<EventStudioImagesResponse> getEventImagesStudioById(@Param("id") Long id);

    @Modifying
    @Query("update EventImagesStudio e set e.isCoverImage = true where e.id = (select s.id from EventImagesStudio s where s.eventStudio = :eventStudio order by s.id limit 1)")
    void updateByCoverImageModifying(@Param("eventStudio") EventStudio eventStudio);

    @Query("select e.imagePath from EventImagesBand e order by e.id")
    List<String> findAllByImagePath();

    @Query("select e.originalImagePath from EventImagesBand e order by e.id")
    List<String> findAllByOriginalImagePath();
}
