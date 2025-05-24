package com.techlab.service;

import com.techlab.model.DetallePedido;
import com.techlab.repository.DetallePedidoRepository;

import java.util.ArrayList;

public class DetallePedidoService {
    DetallePedidoRepository detallePedidos ;


    public DetallePedidoService(DetallePedidoRepository detalleRepo) {
        detallePedidos=detalleRepo;
    }

    public void agregar(DetallePedido detallePedido) {
        this.detallePedidos.guardarDetallePedidos(detallePedido);
    }
    public ArrayList<DetallePedido> listadoPorPedido(int idPedido){
        return (ArrayList<DetallePedido>) detallePedidos.buscarDetallePedidosPorPedidos(idPedido);
    }
    boolean existsByIdProductoAndIdPedido(int idProducto, int idPedido){
        return this.detallePedidos.findByProductoIdAndPedidoId(idProducto,idPedido).isPresent();

    }

}
