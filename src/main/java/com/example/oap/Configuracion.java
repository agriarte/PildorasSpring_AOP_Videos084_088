package com.example.oap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Son necesarias 3 anotaciones:
 * 1- indica que es un archivo de configuración
 * 2- Anotacion que indica que vamos a utilizar POA
 * 3- Donde debe ir a buscar los aspectos
 */

// Son necesarias 3 anotaciones:
// 1- indica que es un archivo de configuración
@Configuration
// 2- Anotacion que indica que vamos a utilizar POA
@EnableAspectJAutoProxy
// 3 - Donde debe ir a buscar los aspectos del proyecto para ejecutarlos automaticamente
// y por detrás cada vez que llamemos a este método.
@ComponentScan({"com.example"})
public class Configuracion {
    
}
