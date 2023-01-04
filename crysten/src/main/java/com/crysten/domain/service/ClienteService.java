package com.crysten.domain.service;

import com.crysten.api.dto.ClienteDTO;
import com.crysten.api.dto.input.ClienteInput;
import com.crysten.domain.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> findAll();

    Page<ClienteDTO> findAllPage(Pageable pageable);

    ClienteDTO findById(Long idCliente);

    ClienteDTO saveCliente(ClienteInput clienteInput);

    ClienteDTO updateCliente(Long idCliente, ClienteInput clienteInput);

    void delete(Long idCliente);

    Endereco consultaCep(String cep);


}
