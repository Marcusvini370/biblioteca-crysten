package com.crysten.domain.service;

import com.crysten.domain.exception.ClienteNotFoundException;
import com.crysten.domain.model.Cliente;
import com.crysten.domain.model.Endereco;
import com.crysten.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService{

    private static final String MSG_CLIENTE_NAO_ENCOTNADO = "Não existe um cadastro de cliente com código %d";

    private ClienteRepository clienteRepository;

    private WebClient webClientViaCep;

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

        Endereco enderecoCep = consultaCep(cliente.getEndereco().getCep());
        cliente.setEndereco(enderecoCep);

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateCliente(Long idCliente, Cliente cliente) {

        Cliente clienteAtual = buscarOuFalhar(idCliente);
        Endereco enderecoCep = consultaCep(cliente.getEndereco().getCep());

        if (clienteAtual.getEndereco().getCep() != cliente.getEndereco().getCep()) {
            BeanUtils.copyProperties(cliente, clienteAtual, "id");
            clienteAtual.setEndereco(enderecoCep);

        } else {
            BeanUtils.copyProperties(cliente, clienteAtual, "id");
        }

        return clienteRepository.save(clienteAtual);
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
    public Endereco consultaCep(String cep) {

        Mono<Endereco> monoEndereco  = this.webClientViaCep
                .method(HttpMethod.GET)
                .uri("/{cep}/json", cep)
                .retrieve()
                .bodyToMono(Endereco.class);

       Endereco enderecoCep = monoEndereco.block();

        return enderecoCep;

    }

}
