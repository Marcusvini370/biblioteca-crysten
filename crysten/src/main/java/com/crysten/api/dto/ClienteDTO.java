package com.crysten.api.dto;

import com.crysten.domain.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Marcus Vinicius Martins")
    private String nomeCompleto;

    @Schema(example = "000.000.000-00")
    private String cpf;

    private Endereco endereco;
}
