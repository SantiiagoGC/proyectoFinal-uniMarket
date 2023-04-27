package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ModeradorServicioTest {

    @Autowired
    private ModeradorServicio moderadorServicio;

    @Autowired
    private ProductoRepo productoRepo;

    @Test
    public void loginTest(){
        try {
            Moderador moderador = moderadorServicio.iniciarSesion("santii0628@gmail.com", "123");
            System.out.println(moderador.getNombre());
            Assertions.assertNotNull(moderador);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void aceptarProductoTest() throws Exception {
        String mensaje = moderadorServicio.aceptarProducto(1);
        Optional<Producto> producto = productoRepo.findById(1);
        System.out.println(mensaje + "? "+producto.get().isActivo());

    }

    @Test
    @Sql("classpath:data.sql")
    public void rechazarProductoTest() throws Exception {
        String mensaje = moderadorServicio.rechazarProducto(1);
        Optional<Producto> producto = productoRepo.findById(1);
        System.out.println(mensaje + "? "+producto.get().isActivo());

    }
}
