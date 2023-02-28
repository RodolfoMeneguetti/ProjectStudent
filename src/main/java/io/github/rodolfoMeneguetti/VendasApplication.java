package io.github.rodolfoMeneguetti;
import io.github.rodolfoMeneguetti.domain.entity.Cliente;
import io.github.rodolfoMeneguetti.domain.entity.Pedido;
import io.github.rodolfoMeneguetti.domain.repository.RepositoryClient;
import io.github.rodolfoMeneguetti.domain.repository.RepositoryPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init (
            @Autowired RepositoryClient repositoryCliente,
            @Autowired RepositoryPedido repositoryPedido){
        return args -> {
            System.out.println(" +++++ Salvando clientes +++++ ");
            repositoryCliente.save(new Cliente ("Paulo Rodolfo"));
            repositoryCliente.save(new Cliente("Meneguetti"));

            Cliente Rodolfo = new Cliente ("Rodolfo");
            repositoryCliente.save(Rodolfo);

            Pedido p = new Pedido ();
            p.setCliente(Rodolfo);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));
            repositoryPedido.save(p);

            System.out.println(" +++++ Listando pedidos atraves do cliente +++++ ");
            Cliente cliente = repositoryCliente.findClienteFetchPedidos(Rodolfo.getId());
            System.out.println(cliente);
            System.out.println(" +++++ Imprimindo Pedidos +++++ ");
            System.out.println(cliente.getPedidos());


            List<Cliente> clienteList = repositoryCliente.findAll();
            clienteList.forEach(System.out::println);

            System.out.println(" +++++ Analisando Clientes +++++ ");
            boolean exists = repositoryCliente.existsByNome("Paulo Rodolfo");
            System.out.println("Existe um cliente com o nome Paulo Rodolfo? " + exists);

            System.out.println(" +++++ Trazendo Lista de pedidos por cliente +++++ ");
            repositoryPedido.findByCliente(Rodolfo).forEach(System.out::println);
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
