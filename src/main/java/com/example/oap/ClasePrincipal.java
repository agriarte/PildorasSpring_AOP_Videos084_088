package com.example.oap;

import com.example.dao.ClienteDAO;
import com.example.dao.ClienteVIPDAO;
import com.example.oap.servicios.MedicionServicio;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 */
@SpringBootApplication
@ComponentScan("com.example")
public class ClasePrincipal {

    public static void main(String[] args) {
        System.out.println("******* pildoras.oap.ClasePrincipal.main()");

        SpringApplication.run(ClasePrincipal.class, args);

        // Leer configuracion de Spring
        AnnotationConfigApplicationContext contexto = new AnnotationConfigApplicationContext(Configuracion.class);
        // Obtener el bean del contenedor de Spring
        ClienteDAO miClienteDAO = contexto.getBean("ClienteDAO", ClienteDAO.class);
        // crear un segundo bean para probar aspectos
        ClienteVIPDAO miClienteVIPDAO = contexto.getBean("clienteVIPDAO", ClienteVIPDAO.class);

        try {
            miClienteDAO.encuentraClientes();
        } catch (Exception e) {
            System.out.println("Excepcion capturada desde try/catch");
        }
        System.out.println("AQUI CONTINUARÍA EJECUCION DEL PROGRAMA");
        
        //************ @Around
        // Obtener el bean del contenedor de Spring
        MedicionServicio miMedicionServicio = contexto.getBean("medicionServicio", MedicionServicio.class);
        
        System.out.println("Llamando al método getServicio()");
        String datos = miMedicionServicio.getServicios();
        
        System.out.println("Servicio Finalizado: " + datos);

        // Cerrar el contexto
        contexto.close();
    }
}
