package com.techlab.exception;

public class ProductoInvalidoException extends RuntimeException {
    public ProductoInvalidoException(String message0,String message1) {

        super("EL "+message0+" TIENE UN VALOR INVALIDO\n Agregue un valor valido\n"+message1);
    }
}
