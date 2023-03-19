package io.github.rodolfoMeneguetti.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    @NotEmpty(message = "{campo.nomeUsuario.obrigatorio}")
    private String userName;

    @Column
    @NotEmpty(message = "{campo.password.obrigatorio}")
    private String password;

    @Column
    private boolean admin;

}
