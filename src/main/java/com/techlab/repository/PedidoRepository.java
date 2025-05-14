package com.techlab.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techlab.model.Cliente;
import com.techlab.model.Pedido;
import com.techlab.model.Producto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PedidoRepository {
    private static final String JSON_PATH = "src/main/resources/data/pedidos.json";
    private final ObjectMapper mapper = new ObjectMapper();
     private ArrayList<Pedido> pedidos;

    public PedidoRepository() {
        this.pedidos = this.cargarPedidos();
    }

    //Cargar Producto desde Json
    private ArrayList<Pedido> cargarPedidos() {
             try {
                File file = new File(JSON_PATH);
                if (!file.exists() || file.length() == 0) {
                    System.out.println("NO EXISTE ARCHIVOS");
                    return new ArrayList<>(); // Si no existe, retorna lista vacía
                }
                return mapper.readValue(file, new TypeReference<ArrayList<Pedido>>() {
                });
            } catch (IOException e) {
                System.err.println("Error al leer el JSON: " + e.getMessage());
                return new ArrayList<>();
            }

    }

    // Guardar pedido en JSON
    private void guardarPedido(ArrayList<Producto> productos) {
        try {
            mapper.writeValue(new File(JSON_PATH), productos);
        } catch (IOException e) {
            System.err.println("Error al guardar en JSON: " + e.getMessage());
        }
    }



    //Ingreso de Cliente
    public void guardar(Pedido pedido) {
        if (pedido == null) throw new IllegalArgumentException("Cliente no puede ser nulo");
        this.pedidos.add(pedido);


    }

}
