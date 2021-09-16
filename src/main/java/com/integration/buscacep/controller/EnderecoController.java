package com.integration.buscacep.controller;

import com.integration.buscacep.model.Endereco;
import com.integration.buscacep.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/v1/buscacep")
public class EnderecoController {

    @Autowired
    public EnderecoService enderecoService;

    @GetMapping
    public Endereco getCep(@PathParam("cep") String cep){
        try {
            var verifyCep = Integer.parseInt(cep);
            if (cep.length() == 8) {
                var endereco = enderecoService.encontraCep(cep);
                return endereco;
            }
            throw new Exception("cep mau formatado");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


}
