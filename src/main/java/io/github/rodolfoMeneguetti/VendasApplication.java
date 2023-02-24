package io.github.rodolfoMeneguetti;

import io.github.rodolfoMeneguetti.Model.Cliente;
import io.github.rodolfoMeneguetti.domain.repository.RepositoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init (@Autowired RepositoryClient repository){
        return args -> {
            repository.salvar(new Cliente ("Paulo Rodolfo"));
            repository.salvar(new Cliente("Meneguetti"));

            List<Cliente> clienteList = repository.listarClientes();
            clienteList.forEach(System.out::println);
        };
    }

    @Value("${application.name}")
    private String MyApplication;

    @GetMapping("/")
    public String HelloWord() {
        return MyApplication;
    }


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
