package com.crysten.api.openapi.controller;

import com.crysten.api.dto.FuncionarioDTO;
import com.crysten.api.dto.input.FuncionarioInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Funcionarios")
public interface FuncionarioControllerOpenApi {

    @Operation(summary = "Lista os funcionarios")
    ResponseEntity<List<FuncionarioDTO>> findAll();

    @Operation(summary = "Busca um funcionário por Id",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "ID do funcionário inválido",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Funcionário não encontrado",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "406", description = "F",
                            content = @Content(schema = @Schema(ref = "Problema406"))
                    )
            })
    ResponseEntity<FuncionarioDTO> findById(@Parameter(description = "ID de um funcionário", example = "1", required = true) Long id);

    @Operation(summary = "Cadastra um funcionário", description = "Cadastro de um funcionário, " +
            "necessita de um cpf e cep válido")
    ResponseEntity<FuncionarioDTO> saveFuncionario(@RequestBody( description = "Representeção de um novo funcionário", required = true) FuncionarioInput funcionarioInput);

    @Operation(summary = "Atualiza um funcionário por ID",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "ID do funcionário inválido",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "funcionário não encontrado",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    ResponseEntity<FuncionarioDTO> updateFuncionario(@Parameter(description = "ID de um funcionário", example = "1", required = true) Long id,
                             @RequestBody(description = "Representeção de um funcionário com dados atualizados", required = true) FuncionarioInput funcionarioInput);

    @Operation(summary = "Excluir um funcionário por ID",responses = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "ID do funcionário inválido",
                    content = @Content(schema = @Schema(ref = "Problema"))
            ),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontradp",
                    content = @Content(schema = @Schema(ref = "Problema"))
            )
    })
    public void deleteFuncionario(@Parameter(description = "ID de um funcionário", example = "1", required = true) Long id);


}
