package com.integration.buscacep.utils;

import com.integration.buscacep.controller.handler.exception.GlobalException;
import com.integration.buscacep.service.EnderecoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnderecoUtils {

    public static String validatedCep(String cep){
       isNumber(cep);
       isCep(cep);
       return cep;
    }
    private static Boolean isNumber(String cep){
        try {
            Integer.parseInt(cep);
            return true;
        }catch (Exception ex){
            throw new GlobalException("CEP inválido");
        }
    }
    private static Boolean isCep(String cep){
        if (cep.length() == 8) {
            return true;
        }
        throw new GlobalException("CEP inválido");
    }
}
