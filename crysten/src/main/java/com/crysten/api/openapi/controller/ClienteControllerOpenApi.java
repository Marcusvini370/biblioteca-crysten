package com.crysten.api.openapi.controller;

import com.crysten.api.dto.ClienteDTO;
import com.crysten.api.dto.input.ClienteInput;
import com.crysten.core.openapi.PageableParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Clientes")
public interface ClienteControllerOpenApi {

    @Operation(summary = "Lista os clientes")
    ResponseEntity<List<ClienteDTO>> findAll();

    @PageableParameter
    @Operation(summary = "Lista os clientes com paginação")
    ResponseEntity<Page<ClienteDTO>> listarClientesPage(Pageable pageable);

    @Operation(summary = "Busca um cliente por Id",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "ID do cliente inválido",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    ResponseEntity<ClienteDTO> findById(@Parameter(description = "ID de um cliente", example = "1", required = true) Long id);

    @PageableParameter
    @Operation(summary = "Busca clientes por nome")
    ResponseEntity<Page<ClienteDTO>> findClientePage(@Parameter(description = "Nome de um cliente", example = "Marcus")String nome, Pageable pageable);

    @Operation(summary = "Cadastra um cliente", description = "Cadastro de um cliente, " +
            "necessita de um cpf e cep válido")
    ResponseEntity<ClienteDTO> saveCliente(@RequestBody( description = "Representeção de um novo cliente", required = true) ClienteInput clienteInput);

    @Operation(summary = "Atualiza um cliente por ID",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "ID do cliente inválido",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    ResponseEntity<ClienteDTO> updateCliente(@Parameter(description = "ID de um cliente", example = "1", required = true) Long id,
                             @RequestBody(description = "Representeção de um cliente com dados atualizados", required = true) ClienteInput clienteInput);

    @Operation(summary = "Excluir um cliente por ID",responses = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "ID do cliente inválido",
                    content = @Content(schema = @Schema(ref = "Problema"))
            ),
            @ApiResponse(responseCode = "404", description = "Cliente não encontradp",
                    content = @Content(schema = @Schema(ref = "Problema"))
            )
    })
    public void deleteCliente(@Parameter(description = "ID de um cliente", example = "1", required = true) Long id);


}
