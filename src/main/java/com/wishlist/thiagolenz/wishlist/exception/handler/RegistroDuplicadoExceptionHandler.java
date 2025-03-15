package com.wishlist.thiagolenz.wishlist.exception.handler;

import com.wishlist.thiagolenz.wishlist.exception.RegistroDuplicadoException;
import com.wishlist.thiagolenz.wishlist.exception.dto.ErrorObjectResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RegistroDuplicadoExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    @ExceptionHandler(RegistroDuplicadoException.class)
    public ErrorObjectResponseDTO handler(RegistroDuplicadoException ex) {
        return new ErrorObjectResponseDTO(
                ex.getMessage(),
                ex.getOrigem()
        );
    }
}
