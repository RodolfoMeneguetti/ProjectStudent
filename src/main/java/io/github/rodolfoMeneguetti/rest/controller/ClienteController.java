package io.github.rodolfoMeneguetti.rest.controller;

import io.github.rodolfoMeneguetti.domain.entity.Cliente;
import io.github.rodolfoMeneguetti.domain.repository.RepositoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private RepositoryClient repository;


    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id){
        Optional <Cliente> cliente = repository.findById(id);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
