package com.crysten.domain.service;

import com.crysten.api.assembler.ClienteInputDissasembler;
import com.crysten.api.assembler.ClienteModelAssembler;
import com.crysten.api.dto.ClienteDTO;
import com.crysten.api.dto.input.ClienteInput;
import com.crysten.domain.exception.CepNotFoundException;
import com.crysten.domain.exception.ClienteNotFoundException;
import com.crysten.domain.model.Cliente;
import com.crysten.domain.model.Endereco;
import com.crysten.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService{

    private static final String MSG_CLIENTE_NAO_ENCOTNADO = "Não existe um cadastro de cliente com código %d";
    private static final String MSG_CEP_NAO_ENCOTNADO = "O CEP digitado está inválido, por favor digite ele corretamente e tente novamente";

    private ClienteRepository clienteRepository;

    private WebClient webClientViaCep;

    private ClienteInputDissasembler clienteInputDissasembler;

    private ClienteModelAssembler clienteModelAssembler;

    @Override
    public List<ClienteDTO> findAll() {
        return clienteModelAssembler.toCollectionModel(clienteRepository.findAll()) ;
    }

    @Override
    public Page<ClienteDTO> findAllPage(Pageable pageable) {
        Page<Cliente> clientesPage = clienteRepository.findAll(pageable);
        List<ClienteDTO> clienteDTO = clienteModelAssembler.toCollectionModel(clientesPage.getContent());
        Page<ClienteDTO> clienteDTOPage = new PageImpl<>(clienteDTO, pageable, clientesPage.getTotalElements());
        return clienteDTOPage;
    }

    @Override
    public ClienteDTO findById(Long idCliente) {
        return clienteModelAssembler.toModel(buscarOuFalhar(idCliente)) ;
    }

    @Override
    public Page<ClienteDTO> findByNomeContaining(String nome, Pageable pageable) {
        return clienteModelAssembler.toCollectionModelPage(clienteRepository.findByNomeContaining(nome.toUpperCase(), pageable));
    }

    @Override
    public ClienteDTO saveCliente(ClienteInput clienteInput) {
        int numero = clienteInput.getEndereco().getNumero();

        Endereco enderecoCep = consultaCep(clienteInput.getEndereco().getCep());

        clienteInput.setEndereco(enderecoCep);
        Cliente cliente = clienteInputDissasembler.toDomainObject(clienteInput);
        cliente.getEndereco().setNumero(numero);

        return clienteModelAssembler.toModel(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDTO updateCliente(Long idCliente, ClienteInput clienteInput) {

        Cliente clienteAtual = buscarOuFalhar(idCliente);
        Endereco enderecoCep = consultaCep(clienteInput.getEndereco().getCep());

        if (clienteAtual.getEndereco().getCep() != clienteInput.getEndereco().getCep()) {
            clienteInputDissasembler.copyToDomainObject(clienteInput, clienteAtual);

            clienteInput.getEndereco().setUf(enderecoCep.getUf());
            clienteInput.getEndereco().setLocalidade(enderecoCep.getLocalidade());
            clienteInput.getEndereco().setBairro(enderecoCep.getBairro());
            clienteInput.getEndereco().setComplemento(enderecoCep.getComplemento());
            clienteInput.getEndereco().setLogradouro(enderecoCep.getLogradouro());

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

        try {

            Mono<Endereco> monoEndereco = this.webClientViaCep
                    .method(HttpMethod.GET)
                    .uri("/{cep}/json", cep)
                    .retrieve()
                    .bodyToMono(Endereco.class);

            Endereco enderecoCep = monoEndereco.block();

            return enderecoCep;

        } catch (Exception e) {
            throw new CepNotFoundException(String.format(String.format(MSG_CEP_NAO_ENCOTNADO)));
        }
    }
}
