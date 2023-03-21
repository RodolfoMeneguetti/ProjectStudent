package io.github.rodolfoMeneguetti;

import io.github.rodolfoMeneguetti.domain.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JWTService {

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken (Usuario usuario) {
        long expString = Long.valueOf(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("emailDoUsuario", "emailUsuario@gmail.com");
        claims.put("roles", "admin");

        return Jwts
                .builder()
                .setSubject(usuario.getUserName())
                .setExpiration(data)
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();

    }

    public static void main (String[] args){
        ConfigurableApplicationContext context =  SpringApplication.run(VendasApplication.class);
        JWTService service =(JWTService) context.getBean(JWTService.class);
        Usuario usuario = Usuario.builder().userName("Rodolfo").build();
        String token = service.gerarToken(usuario);
        System.out.println("Gerar Token  ------------------  ");
        System.out.println(token);

    }

}