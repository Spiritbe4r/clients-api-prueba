package com.oh.apiclients.domain.service;

import com.oh.apiclients.api.dto.ClientWebDTO;
import com.oh.apiclients.domain.dto.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<ClientDTO> findAll();

    ClientDTO createClient(ClientWebDTO clientWebDTO);

    ClientDTO updateClient(Long id, ClientWebDTO clientWebDTO);

    Optional<ClientDTO> findClientById(Long id);

    void deleteClient(Long id);


}
