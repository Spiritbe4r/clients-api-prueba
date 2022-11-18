package com.oh.apiclients.api.facade.impl;

import com.oh.apiclients.api.dto.ClientWebDTO;
import com.oh.apiclients.api.facade.ClientFacade;
import com.oh.apiclients.domain.dto.ClientDTO;
import com.oh.apiclients.domain.service.ClientService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component

public class ClientFacadeImpl implements ClientFacade {

    private final ClientService clientService;

    public ClientFacadeImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientService.findAll();
    }

    @Override
    public ClientDTO createClient(ClientWebDTO clientWebDTO) {
        return clientService.createClient(clientWebDTO);
    }

    @Override
    public ClientDTO updateClient(Long id, ClientWebDTO clientWebDTO) {
        return clientService.updateClient(id, clientWebDTO);
    }

    @Override
    public Optional<ClientDTO> findClientById(Long id) {
        return clientService.findClientById(id);
    }

    @Override
    public void deleteClient(Long id) {
        clientService.deleteClient(id);
    }
}
