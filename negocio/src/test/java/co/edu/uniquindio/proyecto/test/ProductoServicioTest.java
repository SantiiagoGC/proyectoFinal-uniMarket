package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import static org.mockito.Mockito.when;
import java.util.Optional;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ProductoServicioTest {

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void publicarProductoTest() {
        try {
            Usuario vendedor = usuarioServicio.obtenerUsuario("1004844936");

            LocalDate localDateCreado = LocalDate.of(2024, 1, 2);
            LocalDate localDateLimite = LocalDate.of(2024, 1, 2);

            Producto producto = new Producto("Muñeca inflabe", 2,
                    "Muy buena 3 velocidades", 25000.0, true, localDateCreado, localDateLimite, vendedor);

            Producto publicado = productoServicio.publicarProducto(producto);
            Assertions.assertNotNull(publicado);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Sql("classpath:data.sql")
    @Test
    public void listarPorNombreYOPrecio() {
        List<Producto> lista = productoServicio.listarPorNombreYOPrecio("Jabon", 12300.0);
        lista.forEach(System.out::println);


    }

    @Sql("classpath:data.sql")
    @Test
    public void obtenerDescripcionProductoTest() {
        try {
            String descripcionObtenida = productoServicio.obtenerDescripcionProducto(1);
            System.out.printf(descripcionObtenida);
            Assertions.assertEquals("Jabon 2x1", descripcionObtenida);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}


