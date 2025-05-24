package com.techlab.config;

import com.techlab.repository.DetallePedidoRepository;
import com.techlab.repository.PedidoRepository;
import com.techlab.repository.ProductoRepository;
import com.techlab.service.DetallePedidoService;
import com.techlab.service.PedidoService;
import com.techlab.service.ProductoService;



public class DependencyConfig {
    private final ProductoRepository productoRepo;
    private final DetallePedidoRepository detalleRepo;
    private final PedidoRepository pedidoRepo;
    private final ProductoService productoService;
    private final DetallePedidoService detallePedidoService;
    private final PedidoService pedidoService;

    public DependencyConfig() {
        // Inicializa los REPOSITORIOS
        this.productoRepo = new ProductoRepository();
        this.detalleRepo = new DetallePedidoRepository();
        this.pedidoRepo = new PedidoRepository();

        // Inicializa los SERVICIOS (inyectando dependencias)
        this.productoService = new ProductoService(productoRepo);
        this.detallePedidoService = new DetallePedidoService(detalleRepo);
        this.pedidoService = new PedidoService(pedidoRepo, productoService, detallePedidoService);
    }

    // Getters para los servicios
    public PedidoService getPedidoService() {
        return this.pedidoService;
    }

    public DetallePedidoService getDetallePedidoService() {
        return this.detallePedidoService;
    }

    public ProductoService getProductoService() {
        return this.productoService;
    }
}
