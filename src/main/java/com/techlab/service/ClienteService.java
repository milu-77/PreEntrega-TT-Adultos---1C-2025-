package com.techlab.service;

import com.techlab.model.Cliente;
import com.techlab.repository.ClienteRepository;

public class ClienteService {
    ClienteRepository clienteRepository=new ClienteRepository();

    public ClienteService() {
        clienteRepository= new ClienteRepository();
    }
    //CRUD
    public Cliente registrarCliente(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().length()<3) { //VACIO O CORTO
            throw new IllegalArgumentException("No contiene un Nombre Valido inválido");
        }
        if(clienteRepository.buscarPorId(cliente.getId() ).isPresent()) {
            throw new IllegalArgumentException("Cliente ya existente");

        }
        return clienteRepository.guardar(cliente);
    }




}
