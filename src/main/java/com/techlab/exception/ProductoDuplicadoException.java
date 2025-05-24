package com.techlab.exception;

public class ProductoDuplicadoException extends RuntimeException {
    public ProductoDuplicadoException(String message) {

        super("PRODUCTO DUPLICADO\nElija otro "+message);
    }
}
