package com.techlab;

import com.techlab.config.DependencyConfig;
import com.techlab.consoleui.MenuMain;
import com.techlab.consoleui.PedidoUI;
import com.techlab.consoleui.ProductoUI;

public class View {
    static void start (){
        DependencyConfig dependencyConfig = new DependencyConfig();
        PedidoUI pedidoUI=new PedidoUI(dependencyConfig.getPedidoService(),dependencyConfig.getProductoService());
        ProductoUI productoUI= new ProductoUI(dependencyConfig.getProductoService());
        MenuMain menu = new MenuMain(productoUI, pedidoUI);
        menu.mostrarMenu();
    }
}
