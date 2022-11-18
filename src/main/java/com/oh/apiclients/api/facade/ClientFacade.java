package com.oh.apiclients.api.facade;

import com.oh.apiclients.api.dto.ClientWebDTO;
import com.oh.apiclients.domain.dto.ClientDTO;

public interface ClientFacade {

    Iterable<ClientDTO> findAll();

    ClientDTO createClient(ClientWebDTO clientWebDTO);

    ClientDTO updateClient(Long id ,ClientWebDTO clientWebDTO);

    ClientDTO findClientById(Long id);

    void deleteClient(Long id);
}
