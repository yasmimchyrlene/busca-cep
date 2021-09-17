package com.integration.buscacep.controller;

import com.integration.buscacep.model.Endereco;
import com.integration.buscacep.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/v1/buscacep")
public class EnderecoController {

    @Autowired
    public EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<Endereco> getCep(@PathParam("cep") String cep){
        try {
            var verifyCep = Integer.parseInt(cep);
            if (cep.length() == 8) {
                return new ResponseEntity<>( enderecoService.encontraCep(cep), HttpStatus.OK);
            }
            throw new Exception("CEP inválido");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
