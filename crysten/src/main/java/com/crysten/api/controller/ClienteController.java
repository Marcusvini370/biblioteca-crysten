package com.crysten.api.controller;

import com.crysten.domain.model.Cliente;
import com.crysten.domain.model.Endereco;
import com.crysten.domain.service.ClienteService;
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
    public List<Cliente> findAll(){
        return clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Cliente findById(@PathVariable Long id){
        return clienteService.findById(id);
    }

    @ResponseBody
    @GetMapping(value = "/consultarCep/{cep}")
    public ResponseEntity<Endereco> consultaCep(@PathVariable("cep") String cep){
        Endereco enderecoCep = clienteService.consultaCep(cep);
        return new ResponseEntity<Endereco>(enderecoCep, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveCliente(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.updateCliente(id, cliente));
    }

    @DeleteMapping("{id}")
    public void deleteCliete(@PathVariable Long id){
        clienteService.delete(id);
    }


}
