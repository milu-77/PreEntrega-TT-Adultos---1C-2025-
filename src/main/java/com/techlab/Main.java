package com.techlab;

import com.techlab.model.Cliente;
import com.techlab.model.Producto;
import com.techlab.repository.ClienteRepository;
import com.techlab.repository.DetallePedidoRepository;
import com.techlab.repository.PedidoRepository;
import com.techlab.repository.ProductoRepository;
import com.techlab.service.ProductoService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProductoService productos = new ProductoService();
        System.out.println(productos.listarProductos() );
//          System.out.println(productos.buscarProducto(0) );
//         System.out.println(productos.buscarProducto("Balón de Fútbol Adidas") );
//         productos.actualizarProducto(new Producto(0,"Cafetera normal",200,150));
//         System.out.println(productos.buscarProducto(0) );
         productos.borrarProducto(new Producto(0,"Cafetera normal",200,150));
        System.out.println(productos.listarProductos() );


 
// Agregar nuevo producto
//         productos.agregarProducto(new Producto("Laptop HP Pavilion", 899.99, 15));
//        productos.agregarProducto(new Producto("Mouse Inalámbrico Logitech", 25.50, 50));
//        productos.agregarProducto(new Producto("Cafetera Nespresso", 129.99, 10));
//        productos.agregarProducto(new Producto("Paquete 500 Hojas Papel A4", 12.75, 30));
//        productos.agregarProducto(new Producto("Balón de Fútbol Adidas", 34.90, 20));
//        productos.agregarProducto(new Producto("Camiseta de Algodón Unisex", 19.99, 100));
//        productos.agregarProducto(new Producto("Aceite de Oliva Extra Virgen 1L", 8.45, 40));
//        productos.agregarProducto(new Producto("Lego Star Wars Millennium Falcon", 149.99, 5));
//        productos.agregarProducto(new Producto("Crema Hidratante Nivea", 7.30, 60));
//        productos.agregarProducto(new Producto("Comida para Perros Royal Canin 3kg", 22.40, 25));

//        clientes.guardarCliente(new Cliente("Luis González"));
//        clientes.guardarCliente(new Cliente("Ana Martínez"));


//        pedidos.crearPedido(clientes.getIdCliente(0));
//
//        pedidos.agregarProducto(productos.getIdProducto(0);10);


    }
}