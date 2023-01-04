package com.crysten.domain.service;

import com.crysten.api.assembler.ClienteInputDissasembler;
import com.crysten.api.assembler.ClienteModelAssembler;
import com.crysten.domain.dto.ClienteDTO;
import com.crysten.domain.dto.input.ClienteInput;
import com.crysten.domain.exception.ClienteNotFoundException;
import com.crysten.domain.model.Cliente;
import com.crysten.domain.model.Endereco;
import com.crysten.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
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

    private ClienteInputDissasembler clienteInputDissasembler;

    private ClienteModelAssembler clienteModelAssembler;

    @Override
    public List<ClienteDTO> findAll() {
        return clienteModelAssembler.toCollectionModel(clienteRepository.findAll()) ;
    }

    @Override
    public ClienteDTO findById(Long idCliente) {
        return clienteModelAssembler.toModel(buscarOuFalhar(idCliente)) ;
    }

    @Override
    public ClienteDTO saveCliente(ClienteInput clienteInput) {

        Endereco enderecoCep = consultaCep(clienteInput.getEndereco().getCep());
        clienteInput.setEndereco(enderecoCep);

        Cliente cliente = clienteInputDissasembler.toDomainObject(clienteInput);

        return clienteModelAssembler.toModel(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDTO updateCliente(Long idCliente, ClienteInput clienteInput) {

        Cliente clienteAtual = buscarOuFalhar(idCliente);
        Endereco enderecoCep = consultaCep(clienteInput.getEndereco().getCep());

        if (clienteAtual.getEndereco().getCep() != clienteInput.getEndereco().getCep()) {
            clienteInputDissasembler.copyToDomainObject(clienteInput, clienteAtual);
            clienteAtual.setEndereco(enderecoCep);

        } else {
            clienteInputDissasembler.copyToDomainObject(clienteInput, clienteAtual);
        }

        return clienteModelAssembler.toModel(clienteRepository.save(clienteAtual));
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
