package com.techlab.service;

import com.techlab.exception.ProductoCrashException;
import com.techlab.exception.ProductoNotFoundException;
import com.techlab.model.DetallePedido;
import com.techlab.model.Pedido;
import com.techlab.model.Producto;
 import com.techlab.repository.PedidoRepository;

import java.util.ArrayList;

public class PedidoService {
    private final PedidoRepository pedidoRepo;
    private final ProductoService productoService;
    private final DetallePedidoService detalleService;

    public PedidoService(
            PedidoRepository pedidoRepo,
            ProductoService productoRepo,
            DetallePedidoService detalleRepo
    ) {
        this.pedidoRepo = pedidoRepo;
        this.productoService = productoRepo;
        this.detalleService = detalleRepo;
    }

    public void crearPedido(Pedido pedido) {
        pedidoRepo.guardar(pedido);
    }

    public void agregarProducto(int idPedido, int idProducto,int stockPedido) {
        System.out.println(verificarExistenciaPedido(  idPedido));//validad existe pedido
        System.out.println(productoService.verificarExistenciaProducto(idProducto));//validad existe el producto
        productoService.validarStock(idProducto,stockPedido);//validad stock ok
    }

    private boolean verificarExistenciaPedido(int idPedido) {
         return this.pedidoRepo.buscar(idPedido)!=null;
        }


    public String listarPedido() {
        StringBuilder retorno= new StringBuilder();
        if(pedidoRepo.isEmpty()){
            throw new ProductoCrashException("PEDIDOS VACIO");
        }
        retorno.append("\n▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n");
         for(int a =1;a<pedidoRepo.getSize()+1;a++){
                retorno.append("Pedido N°"+a+"\n")
                        .append("Cantidad de productos: "+this.getPedidoById(a).CantidadProductos()+"\n")
                        .append("                 _DETALLES_\n")
                        .append("|      PRODUCTO      | CANTIDAD |  P. Unit |\n");
                for (DetallePedido dp : this.detalleService.listadoPorPedido(a) ){

                    retorno.append("|");
                        try{
                            retorno.append(String.format("%-20s", productoService.buscarProducto(dp.getIdProducto()).getNombre())) ;

                        }catch (ProductoNotFoundException e){
                            retorno.append(String.format("%-20s", "DESCONTINUADO"));
                        }


                    retorno.append("|")
                            .append(String.format("%-10s","   "+dp.getCantidad()))
                            .append("|")
                            .append(String.format("%-10s","   "+dp.getPrecio()))
                            .append("|")
                            .append( "\n");
                }
             retorno.append("__________________________________________________\n")
                     .append("PRECIO TOTAL:  ")
                     .append(String.format("%20s",  +this.getPedidoById(a).getTotal() ))
                       .append("$\n__________________________________________________ \n\n")
                    .append("\n▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n");





         }


        return retorno.toString();
    }

    private Pedido getPedidoById(int a) {
        return this.pedidoRepo.buscar(a);
    }

    public void actualizarPedido(DetallePedido detallePedido) {
        this.pedidoRepo.agregarDetallePedido(detallePedido);
        this.detalleService.agregar(detallePedido);
    }

    public boolean validarDetallePedidoExistente (int idPedido,int idProducto){
        return this.detalleService.existsByIdProductoAndIdPedido(idProducto,idPedido);
    }


    @Override
    public String toString() {
        StringBuilder retorno = new StringBuilder();
         for (int a =0;a<this.pedidoRepo.getSize();a++){
             System.out.println(this.pedidoRepo.getIdArray(a));
             System.out.println(this.detalleService.listadoPorPedido(this.pedidoRepo.getIdArray(a).getId()));
         }
        return  "";
    }

}
