package com.techlab.consoleui;

import com.techlab.service.PedidoService;
import com.techlab.service.ProductoService;

import java.util.Scanner;

public class MenuMain {
    private final Scanner scanner;

    private final ProductoUI productoUI;
    private final PedidoUI pedidoUI;

    public MenuMain(ProductoUI productoui, PedidoUI pedidoui) {
        this.scanner = new Scanner(System.in);
        this.pedidoUI = pedidoui;
        this.productoUI = productoui;
    }

    public void mostrarMenu() {
        while (true) {
            System.out.println("\n┌──────────────────────────────┐");
            System.out.println("│         MENÚ PRINCIPAL       │");
            System.out.println("├──────────────────────────────┤");
            System.out.println("│ 1. Agregar producto          │");
            System.out.println("│ 2. Listar productos          │");
            System.out.println("│ 3. Buscar/Actualizar producto│");
            System.out.println("│ 4. Eliminar producto         │");
            System.out.println("│ 5. Crear Pedido              │");
            System.out.println("│ 6. Listar pedidos            │");
            System.out.println("│ 7. Salir                     │");
            System.out.println("└──────────────────────────────┘");
            System.out.print("   Seleccione una opción: ");
            int opcion = leerEntero(scanner, 1, 7);
            switch (opcion) {
                case 1 -> this.productoUI.agregarProducto();//UsarSubMenu
                case 2 -> this.productoUI.listarProductos();//directo Service
                case 3 -> this.productoUI.buscarActualizarProducto();//UsarSubMenu
                case 4 -> this.productoUI.borrarProducto();//UsarSubMenu
                case 5 -> this.pedidoUI.crearPedido();//UsarSubMenu
                case 6 -> this.pedidoUI.listarPedidos();//Directo Service
                case 7 -> {
                    System.out.println("Saliendo...");
                    return;
                }
            }
        }
    }

     public static int leerEntero(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) return input;
                System.out.printf("Error: Ingrese un número entre %d y %d: ", min, max);
            } catch (NumberFormatException e) {
                System.out.print("Error: Ingrese un número válido: ");
            }
        }
    }
}


