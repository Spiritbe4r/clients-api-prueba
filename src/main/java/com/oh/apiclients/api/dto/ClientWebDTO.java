package com.oh.apiclients.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ClientWebDTO {

    private String name;
    private String lastName;
    private String address;
    private String email;
    private String cellPhone;
}
