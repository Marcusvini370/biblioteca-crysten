package com.crysten.api.controller;

import com.crysten.api.dto.ClienteDTO;
import com.crysten.api.dto.input.ClienteInput;
import com.crysten.api.openapi.controller.ClienteControllerOpenApi;
import com.crysten.domain.model.Endereco;
import com.crysten.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cliente")
@AllArgsConstructor
public class ClienteController implements ClienteControllerOpenApi {

    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> listarClientesPage(
            @PageableDefault(size = 5, sort = "nomeCompleto") Pageable pageable) {
        return ResponseEntity.ok(clienteService.findAllPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @GetMapping("/find/{nome}/page")
    public ResponseEntity<Page<ClienteDTO>> findClientePage(@PathVariable String nome,
                                                            @PageableDefault(size = 5, sort = "nomeCompleto") Pageable pageable) {
        return ResponseEntity.ok(clienteService.findByNomeContaining(nome, pageable));
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Long id){
        clienteService.delete(id);
    }

    @ResponseBody
    @Operation(hidden = true)
    @GetMapping(value = "/consultarCep/{cep}")
    public ResponseEntity<Endereco> consultaCep(@PathVariable("cep") String cep){
        Endereco enderecoCep = clienteService.consultaCep(cep);
        return new ResponseEntity<Endereco>(enderecoCep, HttpStatus.OK);
    }
}
