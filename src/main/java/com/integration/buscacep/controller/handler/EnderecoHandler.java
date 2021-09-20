package com.integration.buscacep.controller.handler;

import com.integration.buscacep.controller.handler.exception.GlobalError;
import com.integration.buscacep.controller.handler.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class EnderecoHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<GlobalError> globalError(Exception ex, HttpServletRequest httpServletRequest,
                                                   HttpServletResponse httpResponse) throws IOException {
        GlobalError globalError = new GlobalError();
        globalError.setCode(400);
        globalError.setMessage(ex.getMessage());
        globalError.setStatus("Bad Request");

        return new ResponseEntity<>(globalError, HttpStatus.BAD_REQUEST);
    }
}
