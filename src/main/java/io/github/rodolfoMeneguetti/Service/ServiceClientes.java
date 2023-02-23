package io.github.rodolfoMeneguetti.Service;

import io.github.rodolfoMeneguetti.Model.Cliente;
import io.github.rodolfoMeneguetti.Repository.RepositoryClientes;
import jdk.nashorn.internal.runtime.JSErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceClientes {

    private RepositoryClientes repository;

    public ServiceClientes (RepositoryClientes repository){
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        repository.persistir(cliente);
    }

    public void validarCliente(Cliente cliente){
        //aplicar validações
    }

}
