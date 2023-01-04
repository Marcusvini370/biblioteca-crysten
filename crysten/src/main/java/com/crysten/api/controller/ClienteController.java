package com.crysten.api.controller;

import com.crysten.domain.dto.ClienteDTO;
import com.crysten.domain.dto.input.ClienteInput;
import com.crysten.domain.model.Endereco;
import com.crysten.domain.service.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cliente")
@AllArgsConstructor
public class ClienteController {

    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @ResponseBody
    @GetMapping(value = "/consultarCep/{cep}")
    public ResponseEntity<Endereco> consultaCep(@PathVariable("cep") String cep){
        Endereco enderecoCep = clienteService.consultaCep(cep);
        return new ResponseEntity<Endereco>(enderecoCep, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> saveCliente(@RequestBody @Valid ClienteInput clienteInput){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveCliente(clienteInput));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> updateCliente(@PathVariable Long id, @RequestBody @Valid ClienteInput clienteInput){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.updateCliente(id, clienteInput));
    }

    @DeleteMapping("{id}")
    public void deleteCliete(@PathVariable Long id){
        clienteService.delete(id);
    }

}
