package io.github.rodolfoMeneguetti;
import io.github.rodolfoMeneguetti.domain.entity.Cliente;
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
            System.out.println(" +++++ Salvando clientes +++++ ");
            repository.salvar(new Cliente ("Paulo Rodolfo"));
            repository.salvar(new Cliente("Meneguetti"));


            List<Cliente> clienteList = repository.listarClientes();
            clienteList.forEach(System.out::println);


            System.out.println(" +++++ Atualizando clientes +++++ ");
            clienteList.forEach(c -> {c.setNome(c.getNome() + " Atualizado.");
            repository.atualizar(c);
            });
            clienteList.forEach(System.out::println);

            System.out.println(" +++++ Buscando por cliente +++++ ");
            repository.buscarPorNome("Men").forEach(System.out::println);

            System.out.println(" +++++ Deletando todos os clientes  +++++ ");
            repository.listarClientes().forEach(c -> {
                repository.deletar(c);
            });
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
