package com.wishlist.thiagolenz.wishlist.exception;

public class RegistroDuplicadoException extends RuntimeException{
    private final Object origem;

    public RegistroDuplicadoException(String message, Object origem) {
        super(message);
        this.origem = origem;
    }

    public Object getOrigem() {
        return origem;
    }
}
