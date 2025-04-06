package kg.geeks.coolband.mapper;

import kg.geeks.coolband.dto.request.ContactsRequest;
import kg.geeks.coolband.dto.response.ContactsResponse;
import kg.geeks.coolband.entities.Contacts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactsMapper {

    ContactsMapper INSTANCE = Mappers.getMapper(ContactsMapper.class);

    @Mapping(target = "id",ignore = true)
    Contacts mapRequestToResponse(ContactsRequest request);

    ContactsResponse map(Contacts entity);

}
