package com.oh.apiclients.api.controllers;

import com.oh.apiclients.api.dto.ClientWebDTO;
import com.oh.apiclients.api.facade.ClientFacade;
import com.oh.apiclients.domain.dto.ClientDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private final ClientFacade clientFacade;

    public ClientController(ClientFacade clientFacade) {
        this.clientFacade = clientFacade;
    }

    @GetMapping
    @Operation(method = "getClients", summary = "list of clients", description = "Get list of clients", tags = {"client",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clone created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDTO.class))}),
            @ApiResponse(responseCode = "500", description = "An error occured.", content = @Content)})
    public ResponseEntity<List<ClientDTO>> getClients() {
        var result = clientFacade.findAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/{id}")
    @Operation(method = "getClientById", summary = "get client", description = "Get client by Id", tags = {"client",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "find Client", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Item not Found.", content = @Content)})
    public ResponseEntity<ClientDTO> getClientById(@Parameter(name = "id", description = "Client Identifier", required = true) @PathVariable Long id) {
        var result = clientFacade.findClientById(id);
        if(result.isPresent()){
            return ResponseEntity.ok().body(result.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @Operation(method = "createClient", summary = "create clients", description = "create client", tags = {"client",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDTO.class))}),
            @ApiResponse(responseCode = "500", description = "An error occured.", content = @Content)})
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientWebDTO clientWebDTO) {
        var result = clientFacade.createClient(clientWebDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(value = "/{id}")
    @Operation(method = "updateClient", summary = "update clients", description = "update client by Id", tags = {"client",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client Updated Sucessfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ClientDTO.class))}),
            @ApiResponse(responseCode = "500", description = "An error occured.", content = @Content)})
    public ResponseEntity<ClientDTO> updateClient(@Parameter(name = "id", description = "Client Identifier", required = true)
                                                  @PathVariable Long id, @RequestBody ClientWebDTO clientWebDTO) {
        var result = clientFacade.updateClient(id, clientWebDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(method = "deleteClientById", summary = "delete client", description = "delete client By Id", tags = {"client",})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted"),
            @ApiResponse(responseCode = "404", description = "Client not Found.")})
    public ResponseEntity<Void> deleteClientById(@Parameter(name = "id", description = "Client Identifier", required = true) @PathVariable Long id) {
        clientFacade.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
