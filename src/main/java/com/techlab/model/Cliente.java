package com.techlab.model;

import java.util.ArrayList;

public class Cliente {
    private static int contadorId = 0;
    ;
    private int id;
    private String nombre;
    ArrayList<Integer> idPedidos = new ArrayList<>();

    public Cliente(String nombre) {

        this.nombre = nombre;
        this.id = contadorId++;

    }
    public Cliente(int id, String nombre) {

        this.nombre = nombre;
        this.id = id;

    }


    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Integer> getIdPedidos() {
        return idPedidos;
    }

    public void setIdPedidos(ArrayList<Integer> idPedidos) {
        this.idPedidos = idPedidos;
    }

    public void agregarPedido(int pedido){
        System.out.println("pedido Agregado");
        this.idPedidos.add(pedido);
    }
    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                '}';
    }

    public void agregarIdPedido(int i) {
        this.idPedidos.add(i);
    }
}
