package com.crysten.api.controller;

import com.crysten.domain.model.Endereco;
import com.crysten.domain.model.Funcionario;
import com.crysten.domain.service.FuncionarioService;
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
    public List<Funcionario> findAll(){
       return funcionarioService.findAll();
   }

    @GetMapping("/{id}")
    public Funcionario findById(@PathVariable Long id){
        return funcionarioService.findById(id);
    }

   @ResponseBody
   @GetMapping(value = "/consultarCep/{cep}")
   public ResponseEntity<Endereco> consultaCep(@PathVariable("cep") String cep){
       Endereco enderecoCep = funcionarioService.consultaCep(cep);

       return new ResponseEntity<Endereco>(enderecoCep, HttpStatus.OK);
   }

   @PostMapping
    public ResponseEntity<Funcionario> saveFuncionario(@RequestBody Funcionario funcionario){
       return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.saveFuncionario(funcionario));
   }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> updateFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario){
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.updateFuncionario(id, funcionario));
    }

    @DeleteMapping("{id}")
    public void deleteFuncionario(@PathVariable Long id){
        funcionarioService.delete(id);
    }


}
