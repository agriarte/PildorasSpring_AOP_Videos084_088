package com.example.oap.servicios;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

/**

 */
@Component
public class MedicionServicio {
    public String getServicios() {
        System.out.println("*** Método getServicios()");
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException ex) {
            Logger.getLogger(MedicionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Servicios realizados en 4 segundos";
    }
}
