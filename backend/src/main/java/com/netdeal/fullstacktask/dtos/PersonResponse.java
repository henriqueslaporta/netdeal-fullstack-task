package com.netdeal.fullstacktask.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse implements Serializable {

    private Long id;
    private String name;
    private Integer score;
    private List<PersonResponse> subPersons;
}
