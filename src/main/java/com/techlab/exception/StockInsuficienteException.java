package com.techlab.exception;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(int stock) {
        super("STOCK INSUFICIENTE \n El stock de dicho producto es de : "+stock);
    }
}
