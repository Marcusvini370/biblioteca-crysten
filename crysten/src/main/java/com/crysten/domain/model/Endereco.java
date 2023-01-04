package com.crysten.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Endereco {

    @Schema(example = "01001-000", required = true)
    private String cep;
    @Schema(example = "Praça da Sé")
    private String logradouro;

    @Schema(example = "lado ímpar")
    private String complemento;

    @Schema(example = "Sé")
    private String bairro;

    @Schema(example = "São Paulo")
    private String localidade;

    @Schema(example = "SP")
    private String uf;

    @Schema(example = "10")
    private Integer numero;

}
