package com.oh.apiclients.domain.service.impl;

import com.oh.apiclients.api.dto.ClientWebDTO;
import com.oh.apiclients.domain.dto.ClientDTO;
import com.oh.apiclients.domain.entities.Client;
import com.oh.apiclients.domain.exception.NotFoundException;
import com.oh.apiclients.domain.repository.ClientRepository;
import com.oh.apiclients.domain.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream()
                .map(Client::toClientDTO).collect(Collectors.toList());
    }

    @Override
    public ClientDTO createClient(ClientWebDTO clientWebDTO) {
        var client = Client.builder().address(clientWebDTO.getAddress())
                .email(clientWebDTO.getEmail())
                .cellPhone(clientWebDTO.getCellPhone())
                .name(clientWebDTO.getName())
                .lastName(clientWebDTO.getLastName()).build();


        return Client.toCreateClientDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO updateClient(Long id ,ClientWebDTO clientWebDTO) {

        var clientOpt = clientRepository.findById(id).orElseThrow(()-> new NotFoundException("Client not found with this Id : " + id));

            clientOpt.setName(clientWebDTO.getName());
            clientOpt.setCellPhone(clientWebDTO.getCellPhone());
            clientOpt.setLastName(clientWebDTO.getLastName());
            clientOpt.setEmail(clientWebDTO.getEmail());

        return Client.toClientDTO(clientRepository.save(clientOpt));
    }

    @Override
    public Optional<ClientDTO> findClientById(Long id) {
        var client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new NotFoundException("Client with this id : " + id + " not found");
        }
        return Optional.ofNullable(Client.toClientDTO(client.get()));
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);

    }
}
