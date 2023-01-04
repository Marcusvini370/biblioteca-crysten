package com.crysten.api.dto.input;

import com.crysten.domain.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Setter
@Getter
public class FuncionarioInput {

    @NotBlank
    private String nomeCompleto;

    @CPF
    private String cpf;

    private Endereco endereco;

}
