package kg.geeks.coolband.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import kg.geeks.coolband.dto.request.ContactsRequest;
import kg.geeks.coolband.dto.response.ContactsResponse;
import kg.geeks.coolband.services.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/contacts")
@Tag(name = "Contacts")
public class ContactsController {

    private final ContactsService contactsService;

    @GetMapping
    @PermitAll
    @Operation(summary = "Get contacts", description = "All users can this")
    ContactsResponse get(){
        return contactsService.get();
    }

    @PatchMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @Operation(summary = "Update contact", description = "Only admin can update")
    ContactsResponse updateContactsBand(@RequestBody ContactsRequest contactsRequest) throws IllegalAccessException, NoSuchFieldException {
        return contactsService.patch(contactsRequest);
    }
}