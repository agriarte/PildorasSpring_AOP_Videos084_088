package com.example.aspectos;

import com.example.oap.Cliente;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginAspectos {

    // método aspecto que se ejecuta después.
    // La lista se inyecta automaticamente porque encuentraClientes() tiene un return
    @AfterReturning(
            pointcut = "execution(* com.example.dao.ClienteDAO.encuentraClientes())",
            returning = "listaClientes"
    )
    public void tareasDespuesEncontrarClientes(List<Cliente> listaClientes) {
        System.out.println("Realizando tareas después de encontrar clientes...");
        // Ahora se puede trabajar con la lista. Imprimir lista, hacer comprobaciones, etx
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente);
        }

        // buscar clientes vips
        System.out.println("Buscando VIPS:");
        for (Cliente cliente : listaClientes) {
            if (cliente.getTipo().equals("VIP")) {
                System.out.println(cliente + "ES CLIENTE VIP");
            }
        }
    }

    // método aspecto que captura excepciones
    @AfterThrowing(
            pointcut = "execution(* com.example.oap.servicios.MedicionServicio.getServicios())",
            throwing = "ex")
    public void capturaRunTimeExceptionEncuentraclientes(Throwable ex) {
        System.out.println("ERROR! Simulación de captura de excepciones con anotación ");
    }
    
    
    // método aspecto que se ejecuta siempre después de la llamada con y sin excepciones
    @After("execution(* com.example.dao.ClienteDAO.encuentraClientes())")
    public void ejecutandoTareasConySinExcepcion (JoinPoint joinPoint){
        System.out.println("Ejecutar tareas siempre 'AFTER'");
    }

    @Around("execution(* com.example.oap.servicios.MedicionServicio.getServicios())")
    public Object ejecutarServicio(ProceedingJoinPoint pjp) throws Throwable{
        System.out.println(".........Comienzo de servicios.........");
        long comienzo = System.currentTimeMillis();
        Object resultado = pjp.proceed(); // el objeto pjp apunta al método destino getServicios(), es decir al método que queremos ejecutar
        
        long fin = System.currentTimeMillis();
        
        System.out.println(".........Fin de servicios.........");
        System.out.println("Tiempo empleado en el servicio: " + (fin-comienzo)/1000 + "s");
        return resultado;
    }
    
    
    
    
    // La anotación sirve para ejecutar el método que la lleva al ejecutar uno o más métodos de la clase indicada.
    // En este caso se activa cuando es llamado el método insertarCliente de cualquier clase con anotación componente.
    // Si queremos que sea el método de una clase concreta pondremos la ruta antes: com.example.dao.ClienteDAO.insertaClientes())")
    // 2 - Otra`posibilidad:
    // El patrón "execution(* com.example.dao.ClienteDAO.*(..))" utiliza wildcards para describir cualquier método (*) 
    // con cualquier tipo de retorno (*) y cualquier conjunto de parámetros ((..))). En resumen, esta anotación se 
    // utiliza para aplicar lógica antes de la ejecución de cualquier método en la clase ClienteDAO.
    // @Before("execution(* com.example.dao.ClienteDAO.*(..))")
    // 3 - También se puede utilizar el asterisco (*) para ejecutar el aspecto en todos los métodos que comiencen con el nombre indicado.
    // Por ejemplo, insertaCliente* activará el aspecto para métodos como insertaClienteVIP, insertaClienteNacional, etc.
    @Before("execution(public void insertaClientes())")
    public void antesAspectos() {
        System.out.println("Simulación de usuario logueado");
        System.out.println("Simulación de perfil de usuario correcto");
    }

    // 4 - Aspecto que se ejecuta si inserClientes lleva un parámetro de tipo Cliente
    // Si hubieran más parámetros separados por "," el aspecto solo se ejecutará si coinciden el número y tipo de parámetros
    @Before("execution(public void insertaClientes(com.example.oap.Cliente))")
    public void antesAspectos2() {
        System.out.println("Simulación de llamada a método de aspectos si se llama a insertaClientes (con_parámetro_de_clase)");
    }

    @Before("execution(public void insertaClientes(..))")
    public void antesAspectos3() {
        System.out.println("Simulación de llamada a método de aspectos si se llama con o sin parámetros. Se usan '..' como comodín");
    }

}
