package com.wishlist.thiagolenz.wishlist.exception;

public class LimiteProdutosException extends RuntimeException{
    private final Integer maximo;
    public LimiteProdutosException(String message, Integer maximo) {
        super(message);
        this.maximo = maximo;
    }

    public Integer getMaximo() {
        return maximo;
    }
}
