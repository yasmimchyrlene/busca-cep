package com.integration.buscacep.service;

import com.integration.buscacep.config.RestTemplateIntegration;
import com.integration.buscacep.model.Endereco;
import com.integration.buscacep.model.ViaCep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private RestTemplateIntegration restTemplate;

    public Endereco encontraCep(String cep){
            var viacep = restTemplate
                    .getForObject("https://viacep.com.br/ws/" + cep + "/json/", ViaCep.class);
            return new Endereco(viacep.getCep(), viacep.getLogradouro(), viacep.getBairro(),
                    viacep.getLocalidade(), viacep.getUf());

    }

}
