package com.crysten.api.controller;

import com.crysten.domain.dto.FuncionarioDTO;
import com.crysten.domain.dto.input.FuncionarioInput;
import com.crysten.domain.model.Endereco;
import com.crysten.domain.service.FuncionarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/funcionario")
@AllArgsConstructor
public class FuncionarioController {

   private FuncionarioService funcionarioService;

   @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAll(){
       return ResponseEntity.ok(funcionarioService.findAll());
   }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(funcionarioService.findById(id));
    }

   @ResponseBody
   @GetMapping(value = "/consultarCep/{cep}")
   public ResponseEntity<Endereco> consultaCep(@PathVariable("cep") String cep){
       Endereco enderecoCep = funcionarioService.consultaCep(cep);

       return new ResponseEntity<Endereco>(enderecoCep, HttpStatus.OK);
   }

   @PostMapping
    public ResponseEntity<FuncionarioDTO> saveFuncionario(@RequestBody @Valid FuncionarioInput funcionarioInput){
       return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.saveFuncionario(funcionarioInput));
   }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> updateFuncionario(@PathVariable Long id, @RequestBody @Valid FuncionarioInput funcionarioInput){
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.updateFuncionario(id, funcionarioInput));
    }

    @DeleteMapping("{id}")
    public void deleteFuncionario(@PathVariable Long id){
        funcionarioService.delete(id);
    }

}
