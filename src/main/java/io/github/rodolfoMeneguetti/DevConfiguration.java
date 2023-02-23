package io.github.rodolfoMeneguetti;

import org.springframework.boot.CommandLineRunner;

@Development
public class DevConfiguration {


    public CommandLineRunner executar(){
        return args -> {
            System.out.println("====================================");
            System.out.println("Esta Rodando em Ambiente de Desenvolvimento");
            System.out.println("====================================");
        };
    }

}
