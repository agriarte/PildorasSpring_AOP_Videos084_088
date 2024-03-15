package com.example.dao;

import com.example.oap.Cliente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("ClienteDAO")
public class ClienteDAO {

    public void insertaClientes() {
        System.out.println("Simulación de insertar cliente con éxito.");
    }

    public List<Cliente> encuentraClientes() {
        
        // lanzar excepcion para pruebas
        if (true) throw new RuntimeException("EXCEPCION FORZADA PARA PRUEBAS");
        
        List<Cliente> listaClientes = new ArrayList<>();

        // simular clientes devueltos por bbdd
        Cliente cl1 = new Cliente("Pepe", "Nornal");
        Cliente cl2 = new Cliente("María", "VIP");
        Cliente cl3 = new Cliente("Juan", "Nornal");
        Cliente cl4 = new Cliente("Marga", "Nornal");
        //agregar lista clientes
        listaClientes.add(cl1);
        listaClientes.add(cl2);
        listaClientes.add(cl3);
        listaClientes.add(cl4);
        
        System.out.println("com.example.dao.ClienteDAO.encuentraClientes() Ejecución finalizada de ENCUENTRA CLIENTES");
        return listaClientes;

    }
}
