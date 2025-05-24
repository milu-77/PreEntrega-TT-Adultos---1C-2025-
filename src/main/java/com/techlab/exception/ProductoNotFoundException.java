package com.techlab.exception;

public class ProductoNotFoundException extends RuntimeException {
  public ProductoNotFoundException(String message) {
    super("EL PRODUCTO NO EXISTE\n"+message);
  }
}
