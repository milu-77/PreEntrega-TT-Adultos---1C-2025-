package com.techlab.model;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private static int contadorId = 0;
    private int id;
     private ArrayList<Integer> idDetallePedidos;
     private double total;

    public Pedido(  double total, ArrayList<Integer> idDetallePedidos) {
         this.total = total;
        this.idDetallePedidos = idDetallePedidos;
        this.id=contadorId++;
    }

    public Pedido() {
        this.total = 0;
        this.idDetallePedidos = new ArrayList<>();
        this.id=contadorId++;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Pedido.contadorId = contadorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getIdDetallePedidos() {
        return idDetallePedidos;
    }

    public void setIdDetallePedidos(ArrayList<Integer> idDetallePedidos) {
        this.idDetallePedidos = idDetallePedidos;
    }

    public double getTotal() {
        return total;
    }


    public void agregarDetalle(DetallePedido detallePedido) {
        this.idDetallePedidos.add(detallePedido.getIdProducto());
        this.total+=detallePedido.getCantidad()*detallePedido.getPrecio();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", idDetallePedidos=" + idDetallePedidos.toString() +
                ", total=" + total +
                '}';
    }

    public int CantidadProductos() {
        return this.idDetallePedidos.size();
    }

    public boolean contieneProducto(int id) {
        return this.idDetallePedidos.contains(id);
    }
 }
