package com.wishlist.thiagolenz.wishlist.exception.dto;

public class ErrorObjectResponseDTO {
    private String message;
    private Object origem;

    public ErrorObjectResponseDTO(String message, Object origem) {
        this.message = message;
        this.origem = origem;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getOrigem() {
        return origem;
    }

    public void setOrigem(Object origem) {
        this.origem = origem;
    }
}
