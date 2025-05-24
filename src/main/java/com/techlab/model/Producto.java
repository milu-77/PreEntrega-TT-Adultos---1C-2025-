package com.techlab.model;

import java.util.Locale;

public class Producto {
    private static int contadorId = 0; ;
    private   int id ;

    private String nombre;
    private double precio;
    private int stock;


    public Producto(String nombre, int stock, double precio) {
        this.nombre =  nombre.toLowerCase(Locale.ROOT);
        this.precio = precio;
        this.stock = stock;
        this.id=contadorId++;
        System.out.println(this.id);
    }
    public Producto(int id,String nombre, double precio, int stock) {
        this.nombre = nombre.toLowerCase(Locale.ROOT);
        this.precio = precio;
        this.stock = stock;
        this.id=id;

    }

    public Producto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStockResta(int stock) {
        this.stock-= stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder();
        retorno.append("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n")
                .append("ID : "+id+"\n")
                .append("NOMBRE: "+nombre+"\n")
                .append("STOCK: "+stock+"\n")
                .append("PRECIO: "+precio+"$\n")
                .append("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        return retorno.toString();
    }

    public static void setContadorId(int contadorId) {
        Producto.contadorId = contadorId;
    }

    public static int getContadorId() {
        return contadorId;
    }
}