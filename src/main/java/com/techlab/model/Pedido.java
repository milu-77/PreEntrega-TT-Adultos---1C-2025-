package com.techlab.model;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private static int contadorId = 0;
    private int id;
    private int  idCliente;
    private ArrayList<Integer> idDetallePedidos;
    private Date fecha;
    private double total;

    public Pedido(int idCliente, double total, ArrayList<Integer> idDetallePedidos) {
        this.idCliente = idCliente;
        this.total = total;
        this.idDetallePedidos = idDetallePedidos;
        this.id=contadorId++;
    }

    public Pedido() {
    }

}
