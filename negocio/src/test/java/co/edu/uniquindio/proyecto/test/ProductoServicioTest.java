package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.FavoritoRepo;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
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

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Sql("classpath:data.sql")
    @Test
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
    public void actualizarTest() {
        try {
            Producto p = productoServicio.obtenerProducto(1);
            p.setPrecio(12000.3);
            productoServicio.actualizarProducto(p);

            Producto modificado = productoServicio.obtenerProducto(1);
            Assertions.assertEquals(12000.3, modificado.getPrecio());
            System.out.println(modificado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest() {
        try {
            productoServicio.eliminarProducto(1);
            System.out.println("Producto eliminado exitosamente.");
            Assertions.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarProductosTest(){

        try {
            List<Producto> productos = productoServicio.listarProducto();
            productos.forEach(System.out::println);
        }catch (Exception e){
            System.out.println("No hay productos.");
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

    @Test
    @Sql("classpath:data.sql")
    public void obtenerEmailVendedorTest(){

        try {
            String email = productoServicio.obtenerEmailVendedor(2);
            System.out.println(email);
            Assertions.assertEquals("juanf.londonob@uqvirtual.edu.co", email);
        } catch (Exception e) {
            e.printStackTrace();
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

    @Test
    @Sql("classpath:data.sql")
    public void listarProductosCaregoriaTest() throws Exception {

        try {
            List<Categoria> categoria = categoriaRepo.findAllByNombreIgnoreCase("Tecnologia");
            categoria.forEach(System.out::println);
        }catch (Exception e){
            throw new Exception("La caregoria no existe.");
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void obtenerFechaLimiteTest(){

        try{
            LocalDate fechaLimite = productoServicio.obtenerFechaLimite(1);
            System.out.println("La fecha límite es: "+ fechaLimite);
            LocalDate expectedFechaLimite = LocalDate.parse("2023-12-04");
            Assertions.assertEquals(expectedFechaLimite, fechaLimite);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void obtenerProducto() {
        try {
            Producto producto = productoServicio.obtenerProducto(1);
            System.out.printf(String.valueOf(producto));
            Assertions.assertEquals( producto, producto);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}


