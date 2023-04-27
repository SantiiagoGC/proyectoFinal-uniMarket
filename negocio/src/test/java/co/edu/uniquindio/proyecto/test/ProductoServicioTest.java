package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Favorito;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoException;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.FavoritoRepo;
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

    @Autowired
    private FavoritoRepo favoritoRepo;

    @Sql("classpath:data.sql")
    public void publicarProductoTest()  {
        try{
            Usuario vendedor = usuarioServicio.obtenerUsuario("1007531125");

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

        List<Producto> lista = productoServicio.listarPorNombreYOPrecio("Jabon", null);
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
    
    @Sql("classpath:data.sql")
    @Test
    public void crearComentario() throws Exception {

        Usuario usuario = usuarioServicio.obtenerUsuario("1010066053");

        Producto producto = productoServicio.obtenerProducto(1);

        LocalDate localDateCreado = LocalDate.of(2024,1,2);

            Comentario comentario = new Comentario(1, "Ostia puta que malo", localDateCreado,
                    usuario, producto);

            Comentario comentario1 = productoServicio.comentarProducto(comentario);

            System.out.println(comentario1);
            Assertions.assertNotNull(comentario1);
    }

    @Test
    @Sql("classpath:data.sql")
    public void agregarProductoFavoritoTest() throws Exception {
        Producto producto = productoServicio.obtenerProducto(1);
        Usuario usuario = usuarioServicio.obtenerUsuario("1010066053");
        Favorito favorito = new Favorito(1, producto, usuario);
        Favorito favoritoPrueba = productoServicio.guardarProductoFavoritos(favorito);

        System.out.println(favoritoPrueba);
    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarProductoFavoritoTest() throws Exception {

        Producto producto = productoServicio.obtenerProducto(1);
        Usuario usuario = usuarioServicio.obtenerUsuario("1010066053");
        Favorito favorito = new Favorito(2, producto, usuario);

        productoServicio.guardarProductoFavoritos(favorito);

        productoServicio.eliminarProductoFavoritos(favorito);
        Favorito favoritoBuscado = favoritoRepo.findById(2).orElse(null);
        Assertions.assertNull(favoritoBuscado);

    }

}


