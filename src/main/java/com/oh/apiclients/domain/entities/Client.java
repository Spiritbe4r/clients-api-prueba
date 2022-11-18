package com.oh.apiclients.domain.entities;

import com.oh.apiclients.domain.dto.ClientDTO;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String address;
    private String email;
    private String cellPhone;


    public static ClientDTO toClientDTO(Client client) {

        return ClientDTO.builder()
                .id(client.id)
                .address(client.address)
                .name(client.name)
                .cellPhone(client.cellPhone)
                .lastName(client.lastName)
                .email(client.email).build();

    }

    public static ClientDTO toCreateClientDTO(Client client) {

        return ClientDTO.builder()
                .address(client.address)
                .name(client.name)
                .cellPhone(client.cellPhone)
                .lastName(client.lastName)
                .email(client.email).build();

    }
}
