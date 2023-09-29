package com.netdeal.fullstacktask.mappers;

import com.netdeal.fullstacktask.dtos.PersonResponse;
import com.netdeal.fullstacktask.entities.Person;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonResponseMapper {
    PersonResponse personToPersonResponse(Person source);

}
