package com.integration.buscacep.controller;

import com.integration.buscacep.controller.handler.exception.GlobalException;
import com.integration.buscacep.model.Endereco;
import com.integration.buscacep.service.EnderecoServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EnderecoControllerTest {
    @InjectMocks
    private EnderecoController enderecoController;

    @Mock
    private EnderecoServiceImpl enderecoServiceImpl;

    private Endereco endereco;

    @Before
    public void setUp(){
        endereco = new Endereco("cep","rua","bairro",
                "cidade","estado");
    }

    @Test
    public void buscandoOEnderecoComOCEPERetornandoEndereco() throws Exception {
        when(enderecoServiceImpl.encontraCep(anyString())).thenReturn(endereco);
        var result = enderecoController.getCep("04094050");
        Assert.assertEquals("deveria retornar o cep encontrado",endereco,result.getBody());
    }
    @Test
    public void buscandoOEnderecoComOCEPERetornando200() throws Exception {
        when(enderecoServiceImpl.encontraCep(anyString())).thenReturn(endereco);
        var result = enderecoController.getCep("04094050");
        Assert.assertEquals("deveria retornar o status do CEP",
                HttpStatus.OK,result.getStatusCode());
    }
    @Test(expected = GlobalException.class)
    public void buscandoOEnderecoComOCEPERetornando400CEPRequerNumeros() throws Exception {
         enderecoController.getCep("errrrror");
    }
    @Test(expected = GlobalException.class)
    public void buscandoOEnderecoComOCEPERetornando400FormatoDoCepInvalido() throws Exception {
         enderecoController.getCep("000000000");
    }

}
