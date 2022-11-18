package com.oh.apiclients.api.dto;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientWebDTO {

    private String name;
    private String lastName;
    private String address;
    private String email;
    private String cellPhone;
}
