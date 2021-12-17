package com.gft.estudoapi.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.gft.estudoapi.dto.security.AutenticacaoDTO;
import com.gft.estudoapi.dto.security.TokenDTO;
import com.gft.estudoapi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Date;

@Service
public class AutenticacaoService{

    @Autowired
    private AuthenticationManager authManager;

    @Value("${estudoapi.jwt.expiration}")
    private String expiration;
    @Value("${estudoapi.jwt.issuer}")
    private String issuer;
    @Value("${estudoapi.jwt.secret}")
    private String secret;

    public TokenDTO autenticar(AutenticacaoDTO authForm) throws AuthenticationException {

        Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(authForm.getEmail(), authForm.getSenha()));

        String token = gerarToken(authenticate);

        return new TokenDTO(token);

    }

    private Algorithm criarAlgoritmo(){

        return Algorithm.HMAC256(secret);

    }

    private String gerarToken(Authentication authenticate) {

        User principal = (User) authenticate.getPrincipal();

        Date hoje = new Date();
        Date expiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return JWT.create()
                .withIssuer(issuer)
                .withExpiresAt(expiracao)
                .withSubject(principal.getId().toString())
                .sign(this.criarAlgoritmo());

    }

    public boolean verificaToken(String token){

        try {
            if (token == null) {
                return false;
            }

            JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token);
            return true;
        }catch (JWTVerificationException e){
            return false;
        }

    }

    public Long retornarIdUsuario(String token){

        String subject = JWT.require(this.criarAlgoritmo()).withIssuer(issuer).build().verify(token).getSubject();

        return Long.parseLong(subject);

    }

}

