package com.crysten.domain.service;

import com.crysten.domain.model.Cliente;
import com.crysten.domain.model.Endereco;

import java.util.List;

public interface ClienteService {

    List<Cliente> findAll();

    Cliente findById(Long idCliente);

    Cliente saveCliente(Cliente cliente);

    Cliente updateCliente(Long idCliente, Cliente cliente);

    void delete(Long idCliente);

    Endereco consultaCep(String cep);


}
