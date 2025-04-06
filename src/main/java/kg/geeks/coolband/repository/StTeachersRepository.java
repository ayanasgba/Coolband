package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.StTeachersResponse;
import kg.geeks.coolband.entities.StTeachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StTeachersRepository extends JpaRepository<StTeachers,Long> {

    @Query("select new kg.geeks.coolband.dto.response.StTeachersResponse(e.id, e.name,e.image,e.bluer,e.urlInstagram,e.position,e.description,e.orientation) from StTeachers e order by e.id")
    List<StTeachersResponse> getAll();


    @Query("select e.imagePath from StTeachers e order by e.id")
    List<String> findAllByImagePath();

    Optional<StTeachersResponse> getStTeachersById(Long id);

}
