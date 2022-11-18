package com.oh.apiclients.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oh.apiclients.api.dto.ClientWebDTO;
import com.oh.apiclients.api.facade.ClientFacade;
import com.oh.apiclients.api.facade.impl.ClientFacadeImpl;
import com.oh.apiclients.domain.dto.ClientDTO;
import com.oh.apiclients.domain.entities.Client;
import com.oh.apiclients.domain.repository.ClientRepository;
import com.oh.apiclients.domain.service.ClientService;
import com.oh.apiclients.domain.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {

    private static ClientRepository clientRepository;
    private static ClientService clientService;
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private static final String BASE_URL = "http://localhost:8080/clients";
    private static final String TEST = "test";

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        clientService = new ClientServiceImpl(clientRepository);

        JacksonTester.initFields(this, new ObjectMapper());
        ClientFacade clientFacade = new ClientFacadeImpl(clientService);
        ClientController clientController = new ClientController(clientFacade);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController)

                .build();

    }

    @Test
    void getClients() throws Exception {

        given(clientRepository.findAll())
                .willReturn(List.of(getClient()));

        MockHttpServletResponse response = mockMvc.perform(
                        get(BASE_URL)
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void getClientById() throws Exception {
        var clientId = 1L;
        var client = getClient();


        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientService.findClientById(1L)).thenReturn(Optional.of(getClientDto()));
        mockMvc.perform(get(BASE_URL + "/", clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void createClient() throws Exception {
        var request = getClientWebDto();
        var client = new Client();
        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setLastName(request.getLastName());
        client.setCellPhone(request.getCellPhone());

        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(clientService.createClient(request)).thenReturn(getClientDto());
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()));
    }

    @Test
    void updateClient() throws Exception {

        long id = 1L;

        Client client = new Client(id, "Carlos", "Diaz", "Lima", "prueba@gmail.com", "3534543534");
        Client updatedClient = new Client(id, "Luis", "Diaz", "Lima", "prueba7@gmail.com", "23423424");

        when(clientRepository.findById(id)).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);

        mockMvc.perform(put(BASE_URL + "/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(updatedClient.getName()))
                .andExpect(jsonPath("$.lastName").value(updatedClient.getLastName()))
                .andExpect(jsonPath("$.email").value(updatedClient.getEmail()))
                .andDo(print());
    }

    @Test
    void deleteClientById() throws Exception {

        long id = 1L;

        doNothing().when(clientRepository).deleteById(id);
        mockMvc.perform(delete(BASE_URL + "/{id}", id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }


    ClientDTO getClientDto() {
        return ClientDTO.builder()
                .id(1L)
                .email(TEST)
                .cellPhone(TEST)
                .lastName(TEST)
                .name(TEST).build();
    }

    ClientWebDTO getClientWebDto() {
        return ClientWebDTO.builder()
                .email(TEST)
                .cellPhone(TEST)
                .lastName(TEST)
                .name(TEST).build();
    }

    Client getClient() {
        Client client = new Client();
        client.setId(1L);
        client.setEmail("cardenascode7@gmail.com");
        client.setLastName(TEST);
        client.setName(TEST);
        client.setCellPhone(TEST);
        client.setAddress(TEST);
        return client;
    }
}