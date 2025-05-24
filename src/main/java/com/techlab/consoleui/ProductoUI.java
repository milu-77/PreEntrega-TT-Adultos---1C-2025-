package com.techlab.consoleui;

import com.techlab.exception.PedidoCrashException;
import com.techlab.exception.ProductoCrashException;
import com.techlab.exception.ProductoDuplicadoException;
import com.techlab.exception.ProductoNotFoundException;
import com.techlab.model.Producto;
import com.techlab.service.ProductoService;

import java.util.Scanner;

public class ProductoUI {
    private final ProductoService productoService;
    private final Scanner scanner;

    public ProductoUI(ProductoService productoService) {
        this.productoService = productoService;
        this.scanner = new Scanner(System.in);

    }

    public void listarProductos() {
        System.out.println(this.productoService.listarProductos());

    }

    public void agregarProducto() {

            System.out.println("\n--- AGREGAR PRODUCTO ---");
        String nombre;
            try {
                  nombre = this.validarNombre(scanner, 3);
                if(nombre.equals("ERROR")){
                    throw new ProductoCrashException("ERROR AL INGRESAR PRODUCTO");
                }
            }catch (ProductoCrashException e){
                System.out.println(e);
                return;
            }
            int stock = this.validarStock(scanner);
            double precio = this.validarPrecio(scanner);
            try{
                this.productoService.agregarProducto(new Producto(nombre,stock,precio));
                System.out.println("Producto Agregado con Exito");
            } catch (Exception e) {
                System.out.println("Error al agregar producto: " + e.getMessage());
            }


    }

    public void buscarActualizarProducto() {
        System.out.println("--- BUSCAR PRODUCTO ---");
        int idProducto;
        try {
             idProducto = validarIdProducto(scanner);
            if(idProducto==-1){
                throw new ProductoCrashException("DEMASIADOS INTENTOS");
            }

        }catch (ProductoCrashException e){
            System.out.println(e.getMessage());
            return;
        }


        System.out.println("El Producto es: ");
        System.out.println(this.productoService.buscarProducto(idProducto).toString());//MEJORAR VISUALIZACION
        System.out.println("DESEA ACTUALIZARLO?");
        System.out.println("1) SI");
        System.out.println("2) NO");
        int opcion = MenuMain.leerEntero(scanner,1,2);
        switch (opcion) {
            case 1 -> ActualizarProducto(idProducto);//UsarSubMenu
            case 2 -> {
                System.out.println("SALIR...");
                return;
            }
        }
    }

    private void ActualizarProducto(int idProducto) {

        System.out.println("\n--- ACTUALIZANDO PRODUCTO ---");
        String nombre = this.validarNombre(scanner, 3);
        if(nombre.equals("ERROR")){
            throw new ProductoCrashException("ERROR AL INGRESAR PRODUCTO");
        }
        int stock = this.validarStock(scanner);
        double precio = this.validarPrecio(scanner);
        try{
            this.productoService.actualizarProducto(new Producto(idProducto,nombre,precio,stock));
            System.out.println("Producto Agregado con Exito");

        } catch (Exception e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }

    }

    public void borrarProducto() {
        System.out.println("\n--- BORRAR PRODUCTO ---");
        int idProducto;
        try {
            idProducto = validarIdProducto(scanner);
            if(idProducto==-1){
                throw new ProductoCrashException("DEMASIADOS INTENTOS");
            }

        }catch (ProductoCrashException e){
            System.out.println(e.getMessage());
            return;
        }



        System.out.println("El Producto a borrar es:");
        System.out.println(this.productoService.buscarProducto(idProducto));//MEJORAR VISUALIZACION
        System.out.println("SEGURO QUE QUIERE ELIMINARLO?");
        System.out.println("1) BORRAR?");
        System.out.println("2) CANCELAR");
        int opcion = MenuMain.leerEntero(scanner,1,2);
        switch (opcion) {
            case 1 -> this.productoService.borrarProducto(idProducto);//UsarSubMenu
            case 2 -> {
                System.out.println("Cancelando...");
                return;
            }
        }




    }


    private int validarIdProducto(Scanner scanner) {
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

    private String validarNombre(Scanner scanner, int min) {
        int intentos=0;
        while (intentos<5) {
            System.out.print("Nombre del producto: ");
            String input = scanner.nextLine().trim();
            try{
                if (input.length() < min) {
                    intentos++;

                    throw new IllegalArgumentException("El nombre debe tener al menos " + min + " caracteres.");
                }
                if (this.productoService.verificarExistenciaProducto(input.toLowerCase())) {
                    intentos++;
                    throw new ProductoDuplicadoException("NOMBRE");
                }
                 return input;

            }   catch (IllegalArgumentException | ProductoDuplicadoException e) {
            System.out.println("Error: " + e.getMessage());
            continue;
        }
        }
        return "ERROR";
    }

    private double validarPrecio(Scanner scanner) {
        while (true) {
            System.out.print("Precio del producto: ");
            String input = scanner.nextLine().trim();
            try {
                double precio = Double.parseDouble(input);
                try{
                    if (precio <= 0) {
                        throw new IllegalArgumentException("Error: Ingrese un número mayor a cero.");
                    }
                    return precio;
                }catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido (ej: 19.99).");
            }
        }

    }

    private int validarStock(Scanner scanner) {
        while (true) {
            System.out.print("Stock del producto: ");
            String input = scanner.nextLine().trim(); // Leer como String primero
            try {
                int stock = Integer.parseInt(input);
                try{
                    if (stock <= 0) {
                        throw new IllegalArgumentException("Error: Ingrese un número mayor a cero.");
                    }
                    return stock;
                }catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido (ej: 200).");
            }
        }
    }
}
