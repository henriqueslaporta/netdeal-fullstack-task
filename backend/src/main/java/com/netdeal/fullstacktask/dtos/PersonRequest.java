package com.netdeal.fullstacktask.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {
    @NotBlank
    String name;
    @NotBlank
    String password;
    @Positive
    Long parentPerson;
}
