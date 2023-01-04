package com.crysten.api.dto.input;

import com.crysten.domain.model.Endereco;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.br.CPF;

@Setter
@Getter
public class ClienteInput {

    @NotBlank
    private String nomeCompleto;

    @CPF
    private String cpf;

    private Endereco endereco;

}
