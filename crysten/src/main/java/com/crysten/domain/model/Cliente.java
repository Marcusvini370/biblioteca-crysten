package com.crysten.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Cliente {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    @CPF
    private String cpf;

    @Embedded
    private Endereco endereco;

}
