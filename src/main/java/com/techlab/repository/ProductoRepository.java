package com.techlab.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techlab.consoleui.ProductoUI;
import com.techlab.model.DetallePedido;
import com.techlab.model.Producto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductoRepository {
    private static final String JSON_PATH = "src/main/resources/data/productos.json";
    private ArrayList<Producto> productos;
    private final ObjectMapper mapper = new ObjectMapper();


    public ProductoRepository() {
        this.productos = this.cargarProductos();
        System.out.println("_____________________");

        System.out.println("CARGANDO PRODUCTOS...");
        System.out.println("cantidad de productos:"+ this.productos.size());
        System.out.println("El indice mayor es:"+ getIndice());
        Producto.setContadorId(getIndice()+1);
        System.out.println("_____________________");

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
            System.err.println("Error al leer el JSON DE PRODUCTOS: " + e.getMessage());
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
        int indice = this.getIndex(index);
        if (indice == -1) throw new IllegalArgumentException("producto no existente -1");

        this.productos.remove(indice);
        this.guardarProductos(this.productos);

    }

    private int getIndex(int index) {
        return IntStream.range(0, productos.size())
                .filter(i -> productos.get(i).getId() == index)
                .findFirst()
                .orElse(-1);
    }

    //Búsqueda por ID
    public Producto buscar(int id) {
        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public Producto buscarIndex(int id) {
        return productos.get(id);
    }
    public int indexDB(){
        return productos.size();
    }


    //Búsqueda por NOMBRE
    public Producto buscar(String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre().equals(nombre))
                .findFirst()
                .orElse(null);
    }

    //Actualizar Producto ESTO ESTA MAL
    public void actualizar(Producto producto) {
        int index = getIndex(producto.getId());
        System.out.println(index+" : "+ producto.getId());
         this.productos.get(index).setNombre(producto.getNombre());
        this.productos.get(index).setPrecio(producto.getPrecio());
        this.productos.get(index).setStock(producto.getStock());
        this.guardarProductos(this.productos);
    }

    //Visualización de Productos BASICA;
    @Override
    public String toString() {
        return productos.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public void limpiar() {
        this.guardarProductos(new ArrayList<Producto>());
    }

    public boolean existsByNombre(String nombre) {

        return buscar(nombre) != null;
    }

    public boolean existsById(int id) {

        return buscar(id) != null;
    }

    public int getIndice() {
        return productos.stream()
                .mapToInt(Producto::getId)
                .max()
                .orElse(0);

    }


    public void actualizarStock(int idProducto, int canditad) {
        int index = getIndex(idProducto);
        System.out.println(index+" : "+ idProducto);
         this.productos.get(index).setStockResta(canditad);
        this.guardarProductos(this.productos);

    }
}
