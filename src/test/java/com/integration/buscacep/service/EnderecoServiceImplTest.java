package com.integration.buscacep.service;

import com.integration.buscacep.config.RestTemplateIntegration;
import com.integration.buscacep.model.Endereco;
import com.integration.buscacep.model.ViaCep;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EnderecoServiceImplTest {
    @InjectMocks
    private EnderecoServiceImpl enderecoServiceImpl;

    @Mock
    private RestTemplateIntegration restTemplateIntegration;

    private Endereco endereco;
    private ViaCep viaCep;

    @Before
    public void setUp(){
        endereco = new Endereco("cep","rua","bairro","cidade","estado");
        viaCep = new ViaCep();
        viaCep.setCep("cep");
        viaCep.setBairro("bairro");
        viaCep.setComplemento("complemento");
        viaCep.setDdd("ddd");
        viaCep.setGia("gia");
        viaCep.setLocalidade("localidade");
        viaCep.setLogradouro("logradouro");
        viaCep.setUf("uf");
        viaCep.setIbge("ibge");
        viaCep.setSiafi("siafi");
    }
    @Test
    public void buscarCepERetornarEnderecoComSucesso(){
        String cep = "cep";
        when(restTemplateIntegration
                .getForObject("https://viacep.com.br/ws/" + cep + "/json/", ViaCep.class))
                .thenReturn(viaCep);
        var result = enderecoServiceImpl.encontraCep(cep);
        Assert.assertEquals("deveria retornar o endereço",endereco.getCep(),result.getCep());
    }
}
