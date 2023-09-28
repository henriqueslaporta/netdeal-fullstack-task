package com.netdeal.fullstacktask.mappers;

import com.netdeal.fullstacktask.dtos.PersonRequest;
import com.netdeal.fullstacktask.entities.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonRequestMapper {
    Person personRequestToPerson(PersonRequest source);

    Person idToPerson(Long id);
}
