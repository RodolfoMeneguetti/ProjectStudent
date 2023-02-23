package io.github.rodolfoMeneguetti;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class DevConfiguration {

    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("====================================");
            System.out.println("Esta Rodando em Ambiente de Desenvolvimento");
            System.out.println("====================================");
        };
    }

}
