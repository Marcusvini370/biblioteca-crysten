package com.crysten.api.dto;

import com.crysten.domain.model.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    private Long id;

    private String nomeCompleto;

    private String cpf;

    private Endereco endereco;
}
