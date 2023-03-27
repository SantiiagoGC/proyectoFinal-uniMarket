package co.edu.uniquindio.uniMarket.test;

import co.edu.uniquindio.uniMarket.entidades.Producto;
import co.edu.uniquindio.uniMarket.entidades.Usuario;
import co.edu.uniquindio.uniMarket.repositorios.ProductoRepo;
import co.edu.uniquindio.uniMarket.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    @Sql("classpath:productos.sql")
    public void registrarTest(){

        Usuario vendedor = usuarioRepo.findById("1007531125").orElse(null);

        Producto producto = new Producto("125", "Play Station", 3, "2x1", 2000000.0, true, vendedor);

        Producto productoGuardado = productoRepo.save(producto);
        System.out.println(productoGuardado);
        Assertions.assertNotNull(productoGuardado);

    }
    @Test
    @Sql("classpath:productos.sql")
    public void eliminarTest(){

        productoRepo.deleteById("123");

        Producto productoBuscado = productoRepo.findById("123").orElse(null);
        Assertions.assertNull(productoBuscado);
    }
    @Test
    @Sql("classpath:productos.sql")
    public void actualizarTest(){

        Producto registrado = productoRepo.findById("123").orElse(null);
        registrado.setPrecio(15.000);
        productoRepo.save(registrado);

        Producto productoBuscado = productoRepo.findById("123").orElse(null);

        Assertions.assertEquals(15.000, productoBuscado.getPrecio());

    }

    @Test
    @Sql("classpath:productos.sql")
    public void listarTest(){

        List<Producto> productosP = productoRepo.findAll();
        productosP.forEach(p -> System.out.println(p));

        //Assertions.assertEquals(2, productosP.size());
    }

}