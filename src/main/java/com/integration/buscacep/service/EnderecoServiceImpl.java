package com.integration.buscacep.service;

import com.integration.buscacep.config.RestTemplateIntegration;
import com.integration.buscacep.controller.handler.exception.GlobalException;
import com.integration.buscacep.model.Endereco;
import com.integration.buscacep.model.ViaCep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private RestTemplateIntegration restTemplate;

    @Override
    public Endereco encontraCep(String cep) {
        var viacep = restTemplate
                .getForObject("https://viacep.com.br/ws/" + cep + "/json/", ViaCep.class);
        var viacepTest = verifyRequest(cep,viacep);
        return new Endereco(viacepTest.getCep(), viacepTest.getLogradouro(), viacepTest.getBairro(),
                viacepTest.getLocalidade(), viacepTest.getUf());

    }
    private ViaCep verifyRequest(String cep, ViaCep viaCep){
        StringBuffer sb = new StringBuffer(cep);
        int z = 1;
        while (ObjectUtils.isEmpty(viaCep.getCep())) {
               for (int i = cep.length(); i >= 0; i--) {
                   for (int a = z; a <=8; a++) {
                       sb.deleteCharAt(i - z);
                       sb.insert(i - z, "0");
                       viaCep = restTemplate
                               .getForObject("https://viacep.com.br/ws/" + sb + "/json/", ViaCep.class);
                       z = z +1;
                       if(sb.toString().contains("00000000")){
                           throw new GlobalException("CEP inválido");
                       }
                       break;
                   }
                   break;
               }
           }
            return viaCep;
    }

}
