package com.techlab.service;

import com.techlab.exception.ProductoDuplicadoException;
import com.techlab.exception.ProductoInvalidoException;
import com.techlab.exception.ProductoNotFoundException;
import com.techlab.exception.StockInsuficienteException;
import com.techlab.model.DetallePedido;
import com.techlab.model.Producto;
import com.techlab.repository.ProductoRepository;

public class ProductoService {
    ProductoRepository productos;

    public ProductoService(ProductoRepository productoRepository) {
        productos = productoRepository;
    }

    // NO DEBE EXISTIR PRODUCTO
    public void agregarProducto(Producto producto) {
        validarProductoNuevo(producto);
        productos.guardar(producto);
    }

    public String listarProductos() {
        //return productos.toString();
        StringBuilder retorno = new StringBuilder();
        retorno.append("LISTADO DE PRODUCTOS\n")
                .append("|  ID  |      PRODUCTO      | CANTIDAD |  P. Unit |\n");

        for (int a = 0; a < this.productos.indexDB(); a++) {
            retorno.append("| ")
                    .append(String.format("%-5s",this.productos.buscarIndex(a).getId()))
                    .append("|  ")
                    .append(String.format("%-18s",this.productos.buscarIndex(a).getNombre()))
                    .append("|  ")
                    .append(String.format("%-8s",this.productos.buscarIndex(a).getStock()))
                    .append("|  ")
                    .append(String.format("%-8s",this.productos.buscarIndex(a).getPrecio()))
                    .append("|")
                    .append( "\n");;

        }
        return retorno.toString();


    }

    public Producto buscarProducto(int id) {
        Producto pro = productos.buscar(id);
        if (pro == null) {
            throw new ProductoNotFoundException("(BP-ERROR DESDE PRODUCTOSERVICE)");
        }
        return productos.buscar(id);
    }

    public Producto buscarProducto(String nombre) {
        Producto pro = productos.buscar(nombre);
        if (pro == null) {
            throw new ProductoNotFoundException("(BP-ERROR DESDE PRODUCTOSERVICE)");
        }
        return productos.buscar(nombre);
    }

    // DEBE EXISTIR PRODUCTO
    public void actualizarProducto(Producto producto) {
        //ACA HAY UN ERROR CONTINUA RD EACA
        validarProductoActualizacion(producto);
        if (verificarExistenciaProducto(producto)) {
            productos.actualizar(producto);
        } else {
            throw new ProductoNotFoundException("AP-ERROR DESDE PRODUCTOSERVICE)");
        }
    }

    public void actualizarProducto(DetallePedido detallePedido) {
        //ACA HAY UN ERROR CONTINUA RD EACA
        validarProductoActualizacion(this.productos.buscar(detallePedido.getIdProducto()));
        if (verificarExistenciaProducto(this.productos.buscar(detallePedido.getIdProducto()))) {
            productos.actualizarStock(detallePedido.getIdProducto(), detallePedido.getCantidad());
        } else {
            throw new ProductoNotFoundException("(AP-ERROR DESDE PRODUCTOSERVICE)");
        }
    }

    private void validarProductoActualizacion(Producto producto) {
        if (producto.getNombre().isEmpty()) {
            throw new ProductoInvalidoException("NOMBRE", "(VPA-ERROR DESDE PRODUCTOSERVICE)");
        }
        if (producto.getPrecio() <= 0) {
            throw new ProductoInvalidoException("PRECIO", "(VPA-ERROR DESDE PRODUCTOSERVICE)");
        }
        if (producto.getStock() < 0) {
            throw new ProductoInvalidoException("Stock", "(VPA-ERROR DESDE PRODUCTOSERVICE)");
        }
    }

    public void borrarProducto(Producto producto) {
        productos.borrar(producto.getId());
    }

    public void borrarProducto(int id) {
        productos.borrar(id);
    }

    private void validarProductoNuevo(Producto producto) {
        validarProductoActualizacion(producto);
        if (this.productos.existsByNombre(producto.getNombre())) {
            throw new ProductoDuplicadoException("nombre)");
        }
        if (this.productos.existsById(producto.getId())) {
            throw new ProductoDuplicadoException("ID");
        }
    }

    public boolean verificarExistenciaProducto(Producto producto) {
        return productos.existsByNombre(producto.getNombre()) || productos.existsById(producto.getId());
    }

    public boolean verificarExistenciaProducto(int idProducto) {
        return productos.existsById(idProducto);
    }

    public boolean verificarExistenciaProducto(String nombre) {
        return productos.existsByNombre(nombre);
    }

    public int getIndice() {
        return this.productos.getIndice();
    }

    public void validarStock(int idProducto, int stockPedido) {
        if (this.productos.buscar(idProducto).getStock() < stockPedido) {
            throw new StockInsuficienteException(this.productos.buscar(idProducto).getStock());
        } else System.out.println("Stock correcto");
    }

    public void actualizarStock(DetallePedido detallePedido) {
        this.actualizarProducto(detallePedido);
    }
}
