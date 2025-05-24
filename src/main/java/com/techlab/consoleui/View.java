package com.techlab.consoleui;

import com.techlab.config.DependencyConfig;

public class View {
    public static void start(){
        DependencyConfig dependencyConfig = new DependencyConfig();
        PedidoUI pedidoUI=new PedidoUI(dependencyConfig.getPedidoService(),dependencyConfig.getProductoService());
        ProductoUI productoUI= new ProductoUI(dependencyConfig.getProductoService());
        MenuMain menu = new MenuMain(productoUI, pedidoUI);
        menu.mostrarMenu();
    }
}
