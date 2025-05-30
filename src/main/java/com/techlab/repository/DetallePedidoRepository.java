package com.techlab.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techlab.model.DetallePedido;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DetallePedidoRepository {
    private static final String JSON_PATH = "src/main/resources/data/detallePedidos.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private ArrayList<DetallePedido> detallePedidos;
    public DetallePedidoRepository() {
        this.detallePedidos = this.cargarDetallePedidos();
        System.out.println("_____________________");
        System.out.println("CARGANDO DETALLES PRODUCTOS...");
        System.out.println("cantidad de Pedidos:"+ this.detallePedidos.size());
        System.out.println("El indice mayor es:"+ getIndice());
        DetallePedido.setContadorId(getIndice()+1);
        System.out.println("_____________________");
    }

    //Cargar Producto desde Json
    private ArrayList<DetallePedido> cargarDetallePedidos() {
        try {
            File file = new File(JSON_PATH);
            if (!file.exists() || file.length() == 0) {
                System.out.println("NO EXISTE ARCHIVOS");
                return new ArrayList<>(); // Si no existe, retorna lista vacía
            }
            return mapper.readValue(file, new TypeReference<ArrayList<DetallePedido>>() {
            });
        } catch (IOException e) {
            System.err.println("Error al leer el JSON DE DETALLE PEDIDO: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    // Guardar DetallePedidos en JSON
    private void guardarDetallePedidos(ArrayList<DetallePedido> detallePedido) {
        try {
            mapper.writeValue(new File(JSON_PATH), detallePedido);
        } catch (IOException e) {
            System.err.println("Error al guardar en JSON: " + e.getMessage());
        }
    }

    public  void guardarDetallePedidos( DetallePedido  detallePedido) {
        this.detallePedidos.add(detallePedido);
        this.guardarDetallePedidos(this.detallePedidos);

    }
    //Borrar Producto
    public void borrarDetallePedidos(int id) {
        this.detallePedidos.remove(id);
        this.guardarDetallePedidos(this.detallePedidos);
    }

    //Búsqueda por ID
    public DetallePedido buscarDetallePedidos(int id) {
        return this.detallePedidos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public DetallePedido buscarDetallePedidosPorPedido(int idPedido) {

        return this.detallePedidos.stream()
                .filter(p -> p.getIdPedido() == idPedido)
                .findFirst()
                .orElse(null);
    }

    public List<DetallePedido> buscarDetallePedidosPorProducto(int idProducto) {
        return this.detallePedidos.stream()
                .filter(p -> p.getId() == idProducto)
                .collect(Collectors.toList());
     }
    public List<DetallePedido> buscarDetallePedidosPorPedidos(int idProducto) {
        return this.detallePedidos.stream()
                .filter(p -> p.getIdPedido()== idProducto)
                .collect(Collectors.toList());
    }



    //Actualizar DetallePedido
    public void actualizarProductos(DetallePedido detallePedido) {
        this.detallePedidos.get(detallePedido.getId()).setIdPedido(detallePedido.getIdPedido());
        this.detallePedidos.get(detallePedido.getId()).setIdProducto(detallePedido.getIdProducto());
        this.detallePedidos.get(detallePedido.getId()).setCantidad(detallePedido.getCantidad());
        this.detallePedidos.get(detallePedido.getId()).setPrecio(detallePedido.getPrecio());

        this.guardarDetallePedidos(this.detallePedidos);
    }

    //Visualización de detallePedido BASICA;


    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder();
        for (DetallePedido detalleP : this.detallePedidos) {
            retorno.append(detalleP.toString()).append("\n");
        }

        return retorno.toString();
    }

    public int getIndice() {
        return detallePedidos.stream()
                .mapToInt(DetallePedido::getId)
                .max()
                .orElse(0);  // Valor por defecto si la lista

    }

    public Optional<DetallePedido> findByProductoIdAndPedidoId(int productoId, int pedidoId){

        return this.detallePedidos.stream()
                .filter(detalle -> ( detalle.getIdPedido()==pedidoId && detalle.getIdProducto()==productoId))
                .findFirst();
    }


    }

