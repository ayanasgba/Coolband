package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.StStudentReviewsResponse;
import kg.geeks.coolband.entities.StStudentReviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StStudentReviewsRepository  extends JpaRepository<StStudentReviews, Long> {

    @Query(value = "select new kg.geeks.coolband.dto.response.StStudentReviewsResponse (e.id,e.name,e.image, e.bluer, e.reviews,e.orientation) from StStudentReviews e order by e.id")
    List<StStudentReviewsResponse> getAll();


    @Query("select e.imagePath from StStudentReviews e order by e.id")
    List<String> findAllByImagePath();
    Optional<StStudentReviewsResponse> getStStudentReviewsById(Long id);
}
