package com.techlab.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techlab.model.Cliente;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class ClienteRepository {
    private static final String JSON_PATH = "src/main/resources/data/clientes.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<Cliente> clientes;

    public ClienteRepository() {
        this.clientes = this.cargarClientes();
    }

    //Cargar Clientes desde Json
    private ArrayList<Cliente> cargarClientes() {
        try {
            File file = new File(JSON_PATH);
            if (!file.exists() || file.length() == 0) {
                System.out.println("NO EXISTE ARCHIVOS");
                return new ArrayList<>(); // Si no existe, retorna lista vacía
            }
            return mapper.readValue(file, new TypeReference<ArrayList<Cliente>>() {
            });
        } catch (IOException e) {
            System.err.println("Error al leer el JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Guardar Clientes en JSON
    private void guardar(ArrayList<Cliente> cliente) {

        try {
            mapper.writeValue(new File(JSON_PATH), cliente);
        } catch (IOException e) {
            System.err.println("Error al guardar en JSON: " + e.getMessage());
        }
    }

    //Ingreso de Cliente
    public Cliente guardar(Cliente cliente) {
        if (cliente == null) throw new IllegalArgumentException("Cliente no puede ser nulo");
        this.clientes.add(cliente);
        this.guardar(this.clientes);
        return cliente;

    }

    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder();
        for (Cliente cli : this.clientes) {
            retorno.append(cli).append("\n");
        }
        return retorno.toString();
    }

    public void limpiar() {
        this.guardar(new ArrayList<Cliente>());
    }

    public Optional<Cliente> buscarPorId(int id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId() == id) // Filtra por ID
                .findFirst(); // Retorna el primero que coincida (o Optional vacío)
    }

}
