package com.techlab.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techlab.model.Producto;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ProductoRepository {
    private static final String JSON_PATH = "src/main/resources/data/productos.json";
    private ArrayList<Producto> productos;
    private final ObjectMapper mapper = new ObjectMapper();


    public ProductoRepository() {
        this.productos = this.cargarProductos();
    }
    //Cargar Producto desde Json
    private ArrayList<Producto> cargarProductos() {
        try {
            File file = new File(JSON_PATH);
            if (!file.exists() || file.length() == 0) {
                System.out.println("NO EXISTE ARCHIVOS");
                return new ArrayList<>(); // Si no existe, retorna lista vacía
            }
            return mapper.readValue(file, new TypeReference<ArrayList<Producto>>() {
            });
        } catch (IOException e) {
            System.err.println("Error al leer el JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Guardar productos en JSON
    private void guardarProductos(ArrayList<Producto> productos) {
        try {
            mapper.writeValue(new File(JSON_PATH), productos);
        } catch (IOException e) {
            System.err.println("Error al guardar en JSON: " + e.getMessage());
        }
    }

    //Ingreso de Productos
    public void guardar(Producto producto) {
        if (producto == null) throw new IllegalArgumentException("producto no puede ser nulo");
        this.productos.add(producto);
        this.guardarProductos(this.productos);
    }

    //Borrar Producto
    public void borrar(int index) {
         int indice= this.getIndex(index);
         if(indice==-1){
             throw new IllegalArgumentException("producto no existente -1");
         }
        this.productos.remove(indice);
        this.guardarProductos(this.productos);
    }

    private int getIndex(int index) {
        for(int indice = 0;indice<this.productos.size();indice++){
            if(this.productos.get(indice).getId()==index){
                return indice;
            }
        }
        return -1;
    }

    //Búsqueda por ID
    public Producto buscar(int id) {
        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
    //Búsqueda por NOMBRE
    public Producto buscar(String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }
    //Actualizar Producto
    public void actualizar(Producto producto) {

        this.productos.get(producto.getId()).setNombre(producto.getNombre());
        this.productos.get(producto.getId()).setPrecio(producto.getPrecio());
        this.productos.get(producto.getId()).setStock(producto.getStock());
        this.guardarProductos(this.productos);
    }

    //Visualización de Productos BASICA;
    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder();
        for (Producto pro : productos) {
            retorno.append(pro.toString()).append("\n");
        }

        return retorno.toString();
    }

    public void limpiar() {
        this.guardarProductos(new ArrayList<Producto>());
    }

    public boolean existsByNombre(String nombre) {

        return buscar(nombre)!=null;
    }

    public boolean existsById(int id) {

        return buscar(id)!=null;
    }
}
