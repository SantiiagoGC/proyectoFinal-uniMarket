package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.UsuarioYProducto;
import co.edu.uniquindio.proyecto.entidades.Favorito;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    public void registrarUsuarioTest(){
        Usuario usuario = new Usuario(1010066053,"Santii0628","url","Santiago","Santiaguito@gmail.com","1234","Cra 11a","323");
        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarTest(){
        usuarioRepo.deleteById(1007531125);
        Usuario usuarioBuscado = usuarioRepo.findById(1007531125).orElse(null);
        Assertions.assertNull(usuarioBuscado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void actualizarTest(){
        Usuario guardado = usuarioRepo.findById(1007531125).orElse(null);
        assert guardado != null;
        guardado.setDireccion("Carrera 4 # 6 - 38");
        usuarioRepo.save(guardado);
        Usuario usuarioBuscado = usuarioRepo.findById(1007531125).orElse(null);
        assert usuarioBuscado != null;
        Assertions.assertEquals("Carrera 4 # 6 - 38", usuarioBuscado.getDireccion());
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarTest(){
        List<Usuario> usuarios = usuarioRepo.findAll();
        usuarios.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:data.sql")
    public void filtrarNombreTest(){
        List<Usuario> lista = usuarioRepo.findAllByNombreContains("Juan");
        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:data.sql")
    public void filtrarEmailTest(){
        Optional<Usuario> usuario= usuarioRepo.findByEmail("juanfr.londonob@uqvirtual.edu.co");

        if (usuario.isPresent()){
            System.out.println(usuario.get());
        }else{
            System.out.println("No existe ese email");
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void validarEmailAndPasswd() {
        Optional<Usuario> usuario = usuarioRepo.findByEmailAndPassword("juanf.londonob@uqvirtual.edu.co", "123456");

        if(usuario.isPresent()) {
            System.out.println("Los datos coinciden");
        } else {
            System.out.println("Los datos no coinciden");
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void paginarListaTest(){
        Pageable paginador = PageRequest.of(0,3);
        Page<Usuario> lista = usuarioRepo.findAll(paginador);
        System.out.println(lista.stream().collect(Collectors.toList()));
    }

    @Test
    @Sql("classpath:data.sql")
    public void validarDatos(){
        Optional<Usuario> usuario = usuarioRepo.findByNombreUsuarioAndPassword("JuanL", "123456");

        if(usuario.isPresent()) {
            System.out.println("Los datos coinciden");
            System.out.println(usuario.get());
        } else {
            System.out.println("Los datos no coinciden");
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void obtenerFavoritos(){
        List<Favorito> favoritos = usuarioRepo.obtenerProductosFavoritos("juanf.londonob@uqvirtual.edu.co");
        favoritos.forEach(System.out::println);
        Assertions.assertEquals(2, favoritos.size());
    }

    @Test
    @Sql("classpath:data.sql")
    public void listarUsuariosProductosTest(){
        List<UsuarioYProducto> respuesta = usuarioRepo.listarUsuarioYProductos();
        respuesta.forEach(System.out::println);
        Assertions.assertEquals(5, respuesta.size());
    }

    // Usuarios que han hecho comentario en tal producto solo 1 vez
    @Test
    @Sql("classpath:data.sql")
    public void listarUsuariosComentariosTest(){
        List<Usuario> usuarios = usuarioRepo.listarUsuariosComentarios(2);
        usuarios.forEach(System.out::println);
        Assertions.assertEquals(2, usuarios.size()); //57:13
    }

}