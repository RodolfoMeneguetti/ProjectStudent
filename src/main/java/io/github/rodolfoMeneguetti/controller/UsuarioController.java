package io.github.rodolfoMeneguetti.controller;


import io.github.rodolfoMeneguetti.Service.impl.UsuarioServiceImpl;
import io.github.rodolfoMeneguetti.domain.entity.Usuario;
import io.github.rodolfoMeneguetti.dto.CredenciaisDTO;
import io.github.rodolfoMeneguetti.dto.TokenDTO;
import io.github.rodolfoMeneguetti.exception.SenhaInvalidaException;
import io.github.rodolfoMeneguetti.security.jwt.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @PostMapping
    @ResponseStatus(CREATED)
    private Usuario salvar(@RequestBody @Valid Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);
        return userService.salvar(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar (@RequestBody CredenciaisDTO credenciais){
            try{
                Usuario usuario = Usuario.builder()
                        .userName(credenciais.getLogin())
                        .password(credenciais.getPassword())
                        .build();
                UserDetails usuarioAutenticado = userService.autenticar(usuario);
                String token = jwtService.gerarToken(usuario);
                return new TokenDTO(usuario.getUserName(), token);

            }catch (UsernameNotFoundException | SenhaInvalidaException e){
                throw new ResponseStatusException(UNAUTHORIZED, e.getMessage());
            }
    }

}
