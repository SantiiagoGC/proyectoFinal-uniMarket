package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    @Sql("classpath:data.sql")
    public void registrarTest() {
        Usuario vendedor = usuarioRepo.findById("1007531125").orElse(null);
        Producto producto = new Producto(1, "Play Station", 3, "2x1", 2000000.0, true, vendedor);
        Producto productoGuardado = productoRepo.save(producto);
        System.out.println(productoGuardado);
        Assertions.assertNotNull(productoGuardado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest() {
        productoRepo.deleteById(1);
        Producto productoBuscado = productoRepo.findById(1).orElse(null);
        Assertions.assertNull(productoBuscado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest() {
        Producto registrado = productoRepo.findById(1).orElse(null);
        assert registrado != null;
        registrado.setPrecio(15.000);
        productoRepo.save(registrado);

        Producto productoBuscado = productoRepo.findById(1).orElse(null);

        assert productoBuscado != null;
        Assertions.assertEquals(15.000, productoBuscado.getPrecio());
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarTest() {
        List<Producto> productos = productoRepo.findAll();
        productos.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:data.sql")
    public void filtrarNombreTest() {
        Optional<Producto> producto = productoRepo.findByNombreContains("iphone");

        if (producto.isPresent()) {
            System.out.println(producto.get());
        } else {
            System.out.println("No existe ese producto");
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void obtenerNombreVendedorTest() {
        String nombre= productoRepo.obtenerNombreVendedor(1);
        Assertions.assertEquals("Juan Londoño", nombre);
    }

    @Test
    @Sql("classpath:data.sql")
    public void obtenerComentariosProducto1() {
        List<Comentario> comentarios =  productoRepo.obtenerComentariosProducto1(2);
        comentarios.forEach(System.out::println);
        Assertions.assertEquals(3, comentarios.size());
    }

    @Test
    @Sql("classpath:data.sql")
    public void obtenerComentariosProducto2() {
        List<Comentario> comentarios =  productoRepo.obtenerComentariosProducto2(2);
        comentarios.forEach(System.out::println);
        Assertions.assertEquals(3, comentarios.size());
    }

    @Test
    @Sql("classpath:data.sql")
    public void obtenerComentariosYProductosTest() {
        List<Object[]> respuesta =  productoRepo.listarProductosYComentarios();
        respuesta.forEach(o -> System.out.println(o[0]+" --- "+o[1]));
        Assertions.assertEquals(5, respuesta.size());
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarProductosValidosTest() {
        List<Object[]> productos =  productoRepo.listarProductosValidos(LocalDate.now());
        productos.forEach(o -> System.out.println(o[0]+" --- "+o[1]+" --- "+o[2]));
       // Assertions.assertEquals(3, respuesta.size());
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarProductosValidosDTOTest() {
        List<ProductoValido> productos =  productoRepo.listarProductosValidosDTO(LocalDate.now());
        productos.forEach(System.out::println);
        // Assertions.assertEquals(3, respuesta.size());
    }

}
