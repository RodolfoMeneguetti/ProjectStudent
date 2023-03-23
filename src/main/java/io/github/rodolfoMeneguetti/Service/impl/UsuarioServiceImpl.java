package io.github.rodolfoMeneguetti.Service.impl;

import io.github.rodolfoMeneguetti.domain.entity.Usuario;
import io.github.rodolfoMeneguetti.domain.repository.UsuarioRepository;
import io.github.rodolfoMeneguetti.exception.SenhaInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository userRepository;

    @Transactional
    public Usuario salvar(Usuario usuario){
        return userRepository.save(usuario);
    }

    public UserDetails autenticar ( Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getUserName());
        boolean senhasCombinam = encoder.matches(usuario.getPassword(), user.getPassword());
        if(senhasCombinam){
            return user;
        }
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
      Usuario usuario = userRepository.findByUserName(userName)
              .orElseThrow( () -> new UsernameNotFoundException("Usuario nao encontrado na base de dados"));

        String[] roles = usuario.isAdmin() ?
                new String[] {"ADMIN", "USER"} : new String[] {"USER"};

       return User
               .builder()
               .username(usuario.getUserName())
               .password(usuario.getPassword())
               .roles(roles)
               .build();

    }
}
