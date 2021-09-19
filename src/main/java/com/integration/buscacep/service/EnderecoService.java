package com.integration.buscacep.service;

import com.integration.buscacep.model.Endereco;

public interface EnderecoService {
    Endereco encontraCep(String cep);
}
