package com.oh.apiclients.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ClientDTO {

    private Long id;
    private String name;
    private String lastName;
    private String address;
    private String email;
    private String cellPhone;
}
