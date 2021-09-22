package com.integration.buscacep.controller;

import com.integration.buscacep.controller.handler.exception.GlobalException;
import com.integration.buscacep.model.Endereco;
import com.integration.buscacep.service.EnderecoServiceImpl;
import com.integration.buscacep.utils.EnderecoUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/v1/endereco")
public class EnderecoController {

    Logger logger = LoggerFactory.getLogger(EnderecoController.class);

    @Autowired
    public EnderecoServiceImpl enderecoServiceImpl;

    @GetMapping
    public ResponseEntity<Endereco> getCep(@PathParam("cep") String cep) {
        logger.info("cep recebido {}", cep);
        EnderecoUtils.validatedCep(cep);
        return new ResponseEntity<>(enderecoServiceImpl.encontraCep(cep), HttpStatus.OK);
    }
}
