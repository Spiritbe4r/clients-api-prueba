package com.oh.apiclients.api.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientWebDTO {

    @NotNull(message = "name is required")
    @Size(min = 2, message = "La minima longitud del nombre es 2")
    private String name;
    private String lastName;
    private String address;
    @NotNull(message = "Email is required")
    private String email;
    @Size(min = 9, message = "La minima longitud del celular es 9")
    @Size(max = 9, message = "La maxima longitud del celular es 9")
    private String cellPhone;
}
