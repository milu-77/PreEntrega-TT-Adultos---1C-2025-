package com.techlab.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techlab.model.DetallePedido;
import com.techlab.model.Pedido;
import com.techlab.model.Producto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PedidoRepository {
    private static final String JSON_PATH = "src/main/resources/data/pedidos.json";
    private final ObjectMapper mapper = new ObjectMapper();
     private ArrayList<Pedido> pedidos;

    public PedidoRepository() {
        this.pedidos = this.cargarPedidos();
        this.purgarPedidos();
        System.out.println("_____________________");
        System.out.println("CARGANDO PEDIDOS...");
        System.out.println("cantidad de Pedidos:"+ this.pedidos.size());
        System.out.println("El indice mayor es:"+ getIndice());
        Pedido.setContadorId(getIndice()+1);
        System.out.println("_____________________");


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
                System.err.println("Error al leer el JSON de PEDIDOS: " + e.getMessage());
                return new ArrayList<>();
            }

    }

    // Guardar pedido en JSON
    private void guardarPedidos(ArrayList<Pedido> productos) {
        try {
            mapper.writeValue(new File(JSON_PATH), productos);
        } catch (IOException e) {
            System.err.println("Error al guardar en JSON: " + e.getMessage());
        }
    }

    //Ingreso de Productos
    public void guardar (Pedido pedido) {
        if (pedido == null) throw new IllegalArgumentException("producto no puede ser nulo");
        this.pedidos.add(pedido);
        this.guardarPedidos(this.pedidos);
    }

    //Borrar Producto
    public void borrar(int index) {
        int indice = this.getIndex(index);
        if (indice == -1) throw new IllegalArgumentException("producto no existente -1");

        this.pedidos.remove(indice);
        this.guardarPedidos(this.pedidos);

    }

    private int getIndex(int index) {
        return IntStream.range(0, pedidos.size())
                .filter(i -> pedidos.get(i).getId() == index)
                .findFirst()
                .orElse(-1);
    }

    //Búsqueda por ID
    public Pedido buscar(int id) {
        return pedidos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public Pedido buscarIndex(int id) {
        return pedidos.get(id);
    }
  //Actualizar Producto
    public void actualizar(Pedido pedido) {
    }

    //Visualización de Productos BASICA;
    @Override
    public String toString() {
        return pedidos.stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public void limpiar() {
        this.guardarPedidos(new ArrayList<Pedido>());
    }



    public boolean existsById(int id) {

        return buscar(id) != null;
    }

    public int getIndice() {
        return pedidos.stream()
                .mapToInt(Pedido::getId)
                .max()
                .orElse(0);  // Valor por defecto si la lista

    }


    public void agregarDetallePedido(DetallePedido detallePedido) {
        int index = getIndex(detallePedido.getIdPedido());
        this.pedidos.get(index).agregarDetalle(detallePedido);
        this.guardarPedidos(this.pedidos);
    }
    public Pedido getIdArray(int a){
        return this.pedidos.get(a);
    }

    public int getSize() {
        return pedidos.size();
    }
    public int getCantidadProducto(int a){
        return this.pedidos.get(a).CantidadProductos();
    }
    public void purgarPedidos(){
        List<Pedido> retorno = pedidos.stream()
                .filter(pedido -> pedido.CantidadProductos() != 0)
                .toList();

         this.guardarPedidos(new ArrayList<>(retorno));
        System.out.println(Arrays.toString(retorno.toArray()));

    }

    public boolean isEmpty() {
        return this.pedidos.isEmpty();
    }
}
