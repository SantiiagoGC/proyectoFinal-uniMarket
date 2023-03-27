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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Test
    public void registrarUsuarioTest(){


        Usuario usuario = new Usuario("1007531125", "Juan Londoño", "juanf.londonob@uqvirtual.edu.co", "123456", "Carrera 15 # 10N - 35", "3103003738");

        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void eliminarTest(){

        usuarioRepo.deleteById("1007531125");

        Usuario usuarioBuscado = usuarioRepo.findById("1007531125").orElse(null);
        Assertions.assertNull(usuarioBuscado);
    }
    @Test
    @Sql("classpath:usuarios.sql")
    public void actualizarTest(){

        Usuario guardado = usuarioRepo.findById("1007531125").orElse(null);
        guardado.setDireccion("Carrera 4 # 6 - 38");
        usuarioRepo.save(guardado);

        Usuario usuarioBuscado = usuarioRepo.findById("1007531125").orElse(null);
        Assertions.assertEquals("Carrera 4 # 6 - 38", usuarioBuscado.getDireccion());

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarTest(){

        List<Usuario> usuarios = usuarioRepo.findAll();
            usuarios.forEach(u -> System.out.println(u));
    }
}
