package com.integration.buscacep.controller;

import com.integration.buscacep.model.Endereco;
import com.integration.buscacep.service.EnderecoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/v1/buscacep")
public class EnderecoController {

    @Autowired
    public EnderecoServiceImpl enderecoServiceImpl;

    @GetMapping
    public ResponseEntity<Endereco> getCep(@PathParam("cep") String cep){
        try {
            var verifyCep = Integer.parseInt(cep);
            if (cep.length() == 8) {
                return new ResponseEntity<>( enderecoServiceImpl.encontraCep(cep), HttpStatus.OK);
            }
            throw new Exception("CEP inválido");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
