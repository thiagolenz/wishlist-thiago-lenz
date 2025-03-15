package com.wishlist.thiagolenz.wishlist.exception.handler;

import com.wishlist.thiagolenz.wishlist.exception.LimiteProdutosException;
import com.wishlist.thiagolenz.wishlist.exception.dto.ErrorObjectResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class LimiteProdutosExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    @ExceptionHandler(LimiteProdutosException.class)
    public ErrorObjectResponseDTO handler(LimiteProdutosException ex) {
        return new ErrorObjectResponseDTO(
                ex.getMessage(),
                "Máximo de registros:" + ex.getMaximo()
        );
    }
}
