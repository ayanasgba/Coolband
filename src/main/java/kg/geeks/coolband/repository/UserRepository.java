package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.UserResponse;
import kg.geeks.coolband.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    Optional<User> getUserByEmail(String email);

    @Query("select new kg.geeks.coolband.dto.response.UserResponse(u.id, CONCAT(u.lastName, ' ', u.firstName), u.email, u.role) from User u")
    List<UserResponse> getAllUser();
}
