package com.crysten.api.dto.input;

import com.crysten.domain.model.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Setter
@Getter
public class FuncionarioInput {

    @Schema(example = "Pedrinho Santos Melo")
    @NotBlank
    private String nomeCompleto;

    @Schema(example = "000.000.000-00", required = true)
    @CPF
    private String cpf;

    private Endereco endereco;

}
