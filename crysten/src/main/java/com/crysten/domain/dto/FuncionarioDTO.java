package com.crysten.domain.dto;

import com.crysten.domain.model.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO {

    private Long id;

    private String nomeCompleto;

    private String cpf;

    private Endereco endereco;
}
