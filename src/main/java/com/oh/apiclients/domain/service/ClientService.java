package com.oh.apiclients.domain.service;

import com.oh.apiclients.api.dto.ClientWebDTO;
import com.oh.apiclients.domain.dto.ClientDTO;
import com.oh.apiclients.domain.entities.Client;
import com.oh.apiclients.domain.repository.ClientRepository;

public interface ClientService {

    Iterable<ClientDTO> findAll();

    ClientDTO createClient(ClientWebDTO clientWebDTO);

    ClientDTO updateClient(Long id ,ClientWebDTO clientWebDTO);

    ClientDTO findClientById(Long id);

    void deleteClient(Long id);


}
