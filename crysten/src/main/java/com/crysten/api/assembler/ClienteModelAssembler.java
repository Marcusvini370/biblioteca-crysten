package com.crysten.api.assembler;

import com.crysten.domain.dto.ClienteDTO;
import com.crysten.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ClienteDTO toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public List<ClienteDTO> toCollectionModel(List<Cliente> Clientes) {
        return Clientes.stream()
                .map(cliente -> toModel(cliente))
                .collect(Collectors.toList());
    }

    public Page<ClienteDTO> toCollectionModelPage(Page<Cliente> page) {
        return page.map(cliente -> toModel(cliente));
    }

}
