package com.gft.estudoapi.controllers;


import com.gft.estudoapi.dto.security.AutenticacaoDTO;
import com.gft.estudoapi.dto.security.TokenDTO;
import com.gft.estudoapi.services.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("v1/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody AutenticacaoDTO authForm){

        try{

            return ResponseEntity.ok(autenticacaoService.autenticar(authForm));

        }catch (AuthenticationException ae){

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        }

    }

}
