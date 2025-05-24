package com.techlab.model;

public class DetallePedido {
    private static int contadorId = 0;
    private int id;
    private int idPedido;
    private int idProducto;
    private double precio;
    private int cantidad;

    public DetallePedido(int idPedido, int idProducto, double precio, int cantidad) {
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.id=contadorId++;
    }


    public DetallePedido() {
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }



    @Override
    public String toString() {
        return "DetallePedido{" +
                "idPedido=" + idPedido +
                ", idProducto=" + idProducto +
                ", precio=" + precio +
                ", canditad=" + cantidad +
                '}';
    }

    public static void setContadorId(int contadorId) {
        DetallePedido.contadorId = contadorId;
    }
}
