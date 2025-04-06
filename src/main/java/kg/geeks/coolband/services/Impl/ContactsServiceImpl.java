package kg.geeks.coolband.services.Impl;

import jakarta.annotation.PostConstruct;
import kg.geeks.coolband.dto.request.ContactsRequest;
import kg.geeks.coolband.dto.response.ContactsResponse;
import kg.geeks.coolband.entities.Contacts;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.ContactsMapper;
import kg.geeks.coolband.repository.ContactsRepository;
import kg.geeks.coolband.services.ContactsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ContactsServiceImpl implements ContactsService {

    private final ContactsRepository contactsRepository;

    @Override
    public ContactsResponse get() {
        return contactsRepository.findContactsById(1L).orElseThrow(() -> new NotFoundException("Contact not found !"));
    }

    @Override
    public ContactsResponse patch(ContactsRequest contactsRequest) throws NoSuchFieldException, IllegalAccessException {
        Contacts contacts = contactsRepository.findById(1L).orElseThrow(null);
        contacts.setId(1L);
        Patch.patchWithNonMediaFields(contactsRequest, contacts);
        contactsRepository.save(contacts);
        return ContactsMapper.INSTANCE.map(contacts);
    }

    @PostConstruct
    public void initSaveContacts() {
        if (contactsRepository.findById(1L).isPresent()) {
            log.info("\nContacts with ID 1L already exist. Skipping creation.\n");
        } else {
            Contacts contacts = new Contacts();
            contacts.setId(1L);
            contactsRepository.save(contacts);
            log.info("\nContacts saved with null fields under ID: %s \n".formatted(contacts.getId()));
        }
    }


}
