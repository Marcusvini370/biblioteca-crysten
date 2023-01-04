package com.crysten.domain.service;

import com.crysten.domain.model.Cliente;
import com.crysten.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService{

    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return null;
    }

    @Override
    public Cliente findById(Long idCliente) {
        return null;
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente updateCliente(Long idCliente, Cliente cliente) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
