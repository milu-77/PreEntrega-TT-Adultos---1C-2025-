package com.techlab.service;

import com.techlab.model.Producto;
import com.techlab.repository.ProductoRepository;

public class ProductoService {
    ProductoRepository productos = new ProductoRepository();

    public ProductoService() {
        productos = new ProductoRepository();
    }

    // NO DEBE EXISTIR PRODUCTO
    public void agregarProducto(Producto producto) {
        validarProducto(producto);
        if (!verificarExistenciaProducto(producto)) {
            productos.guardar(producto);

        }


    }

    public String listarProductos() {
        return productos.toString();
    }

    public Producto buscarProducto(int id) {
        Producto pro = productos.buscar(id);
        if (pro == null) {
            throw new IllegalArgumentException("El Producto NO EXISTE");
        }
        return productos.buscar(id);
    }

    public Producto buscarProducto(String nombre) {
        Producto pro = productos.buscar(nombre);
        if (pro == null) {
            throw new IllegalArgumentException("El Producto NO EXISTE");
        }
        return productos.buscar(nombre);
    }
    // DEBE EXISTIR PRODUCTO

    public void actualizarProducto(Producto producto) {
        validarProducto(producto);
        if (verificarExistenciaProducto(producto)) {
            productos.actualizar(producto);
        }else{
            throw new IllegalArgumentException("El Producto no existe caramba ");

        }


    }

    public void borrarProducto(Producto producto) {
        productos.borrar(producto.getId());

    }


    private void validarProducto(Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        if (producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }

    public boolean verificarExistenciaProducto(Producto producto) {
        return productos.existsByNombre(producto.getNombre()) || productos.existsById(producto.getId());
    }


}
