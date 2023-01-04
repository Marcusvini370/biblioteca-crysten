package com.crysten.domain.service;

import com.crysten.domain.dto.ClienteDTO;
import com.crysten.domain.dto.input.ClienteInput;
import com.crysten.domain.model.Endereco;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> findAll();

    ClienteDTO findById(Long idCliente);

    ClienteDTO saveCliente(ClienteInput clienteInput);

    ClienteDTO updateCliente(Long idCliente, ClienteInput clienteInput);

    void delete(Long idCliente);

    Endereco consultaCep(String cep);


}
