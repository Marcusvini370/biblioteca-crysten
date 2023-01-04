package com.crysten.api.assembler;

import com.crysten.domain.dto.input.ClienteInput;
import com.crysten.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteInputDissasembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cliente toDomainObject(ClienteInput clienteInput) {
        return modelMapper.map(clienteInput, Cliente.class);
    }

    public void copyToDomainObject(ClienteInput clienteInput, Cliente cliente) {
        modelMapper.map(clienteInput, cliente);
    }

}
