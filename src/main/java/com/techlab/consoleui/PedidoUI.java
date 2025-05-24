package com.techlab.consoleui;

import com.techlab.exception.*;
import com.techlab.model.DetallePedido;
import com.techlab.model.Pedido;
 import com.techlab.service.PedidoService;
import com.techlab.service.ProductoService;

import java.util.Scanner;

public class PedidoUI {
    PedidoService pedidoService;
    ProductoService productoService;

    private final Scanner scanner;

    public PedidoUI(PedidoService pedidoService, ProductoService productoService) {
        this.pedidoService = pedidoService;
        this.productoService = productoService;
        this.scanner = new Scanner(System.in);
        ;
    }

    public void crearPedido() {
        System.out.println("--- AGREGAR PRODUCTO ---");
        Pedido pedido = new Pedido();
        System.out.println("Pedido Creado...");

        while(true){
            System.out.println("Que producto desea agregar..");
            System.out.println(productoService.listarProductos());
            try{
                DetallePedido detallePedido= this.prepararDetalle(pedido);

                pedidoService.crearPedido(pedido);
                productoService.actualizarStock(detallePedido);
                pedidoService.actualizarPedido(detallePedido);
            }catch (PedidoCrashException | DetallePedidoDuplicadoException e){
                System.out.println(e.getMessage());
                return;
            }



            System.out.println("Desea Agregar Otro Producto?");
            System.out.println("1) SI");
            System.out.println("2) NO");
            int opcion = MenuMain.leerEntero(scanner,1,2);
            switch (opcion) {
                case 1 -> System.out.println("agregue nuevo producto");//UsarSubMenu
                case 2 -> {
                    System.out.println("SALIR...");
                    return;
                }
            }



        }








    }

    private DetallePedido prepararDetalle(Pedido pedido) {
        System.out.println(pedido.toString());
        int idProducto = validarIdProducto(scanner, pedido);
        if(idProducto ==-1){
            throw new PedidoCrashException("ERROR AL PREPARAR PEDIDO");
        }


         int stock = validarStock(productoService.buscarProducto(idProducto).getStock());
         if(stock ==-1){
             throw new PedidoCrashException("ERROR AL PREPARAR PEDIDO");
         }
         if(this.pedidoService.validarDetallePedidoExistente(pedido.getId(),idProducto) ){
             throw  new DetallePedidoDuplicadoException("DP-ERROR DESDE PEDIDOUI)");
         }
        System.out.println("Producto solicitado");
        System.out.println(productoService.buscarProducto(idProducto));
        System.out.println("Stock Solicitado" + stock);

        return new DetallePedido(pedido.getId(), idProducto, productoService.buscarProducto(idProducto).getPrecio(), stock);
    }

    private int validarStock(int stock) {
        int contador = 0;
        while (contador<5) {
            System.out.print("Ingresar Stock solicitado: ");
            String input = scanner.nextLine().trim();
            try {
                int unidadesPedidos = Integer.parseInt(input);
                if (unidadesPedidos <= 0) {
                    throw new IllegalArgumentException("Error: Ingrese un número mayor a cero.");
                }
                if (unidadesPedidos > stock) {
                    throw new StockInsuficienteException(stock); // Lanza excepción personalizada
                }
                return unidadesPedidos;
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido (ej: 200).");
                contador++;
            } catch (IllegalArgumentException | StockInsuficienteException e) {
                System.out.println(e.getMessage()); // Mensaje claro de error
                contador++;
            }
        }return -1;
    }

    public void listarPedidos() {
        try {
            System.out.println(pedidoService.listarPedido());
        }catch (ProductoCrashException e){
            System.out.println("LISTA DE PEDIDOS VACIA");
        }



    }

    private int validarIdProducto(Scanner scanner,Pedido pedido) {
        int control =0;
        while (control<5) { //MODIFICAR PARA EVITAR BUCLE INFINITO
            System.out.print("Ingresar ID producto: ");
            String input = scanner.nextLine().trim();
            try {
                try{
                    int id = Integer.parseInt(input);
                    if(!this.productoService.verificarExistenciaProducto(id)) {
                        control++;
                        throw new ProductoNotFoundException("(VIP-ERROR DESDE PRODUCTOUI)");

                    }
                    return id;
                }catch (ProductoNotFoundException e) {
                    control++;
                    System.out.println("Error: " + e.getMessage());

                }
            } catch (NumberFormatException e) {
                control++;
                System.out.println("Error: Ingrese un número válido (ej: 200).");
            }
        }
        return -1;

    }
}
