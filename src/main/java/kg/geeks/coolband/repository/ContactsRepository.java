package kg.geeks.coolband.repository;

import kg.geeks.coolband.dto.response.ContactsResponse;
import kg.geeks.coolband.entities.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {

    Optional<ContactsResponse> findContactsById(Long id);

}
