package com.techlab.model;

public class DetallePedido {
    private static int contadorId = 0;
    private int id;
    private int idPedido;
    private int idProducto;
    private double precio;
    private int canditad;

    public DetallePedido(int idCliente, int idProducto, double precio, int canditad) {
        this.idPedido = idCliente;
        this.idProducto = idProducto;
        this.precio = precio;
        this.canditad = canditad;
        this.id=contadorId++;
    }


    public DetallePedido() {
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

    public int getCanditad() {
        return canditad;
    }

    public void setCanditad(int canditad) {
        this.canditad = canditad;
    }

    @Override
    public String toString() {
        return "DetallePedido{" +
                "idCliente=" + idPedido +
                ", idProducto=" + idProducto +
                ", precio=" + precio +
                ", canditad=" + canditad +
                '}';
    }
}
