package com.techlab.exception;

public class DetallePedidoDuplicadoException extends RuntimeException {
    public DetallePedidoDuplicadoException(String message) {
        super("DETALLE PEDIDO DUPLICADO\n"+message );
    }
}
