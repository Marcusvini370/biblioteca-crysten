package com.crysten.domain.service;

import com.crysten.domain.exception.ClienteNotFoundException;
import com.crysten.domain.model.Cliente;
import com.crysten.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService{

    private static final String MSG_CLIENTE_NAO_ENCOTNADO = "Não existe um cadastro de cliente com código %d";

    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Long idCliente) {
        return buscarOuFalhar(idCliente);
    }

    @Override
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Long idCliente, Cliente cliente) {
        buscarOuFalhar(idCliente);

        return clienteRepository.save(cliente);
    }

    @Override
    public void delete(Long idCliente) {
        try {
            clienteRepository.deleteById(idCliente);

        } catch(EmptyResultDataAccessException e){
                throw new ClienteNotFoundException(String.format(MSG_CLIENTE_NAO_ENCOTNADO, idCliente));
            }
    }

    public Cliente buscarOuFalhar(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(String.format(MSG_CLIENTE_NAO_ENCOTNADO, id)));
    }
}
