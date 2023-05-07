package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.modelo.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.modelo.dto.UsuarioPostDTO;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

//import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
//El transactional ayuda para que no afecte la base de datos
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrar() throws Exception{
        UsuarioPostDTO usuarioPostDTO = new UsuarioPostDTO(
                1010066053,
                "Santii0628",
                "Santiago Garcia Cañas",
                "Santii0628@gmail.com",
                "1234",
                "Cra 11a",
                "Inventao",
                "3234327001");
    //Guardamos el registro usando el método del servicio
        Integer nuevo = usuarioServicio.registarUsuario(usuarioPostDTO);
    //Comprobamos que sí haya quedado guardado. Si se guardó debe retornar el código (no 0).
        Assertions.assertNotEquals(0, nuevo);
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void actualizarTest() throws Exception{
    //Para actualizar el cliente primero lo obtenemos
        UsuarioGetDTO guardado = usuarioServicio.obtenerUsuario(1010066053);
    //Le modificamos el número de teléfono (la contraseña la enviamos null)
        UsuarioPostDTO modificado = new UsuarioPostDTO(
                guardado.getCedula(), guardado.getNombreUsuario(), guardado.getNombre(), guardado.getCorreo(),
                null, guardado.getDireccion(), guardado.getFotoPerfil(), "3234327000");
    //El servicio de actualizar nos retorna el cliente
        UsuarioGetDTO actualizado = usuarioServicio.actualizarUsuario(1010066053, modificado);
    //Se comprueba que ahora el teléfono del cliente sea el que se le asignó con la actualización
        Assertions.assertEquals("3234327000", actualizado.getTelefono());
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void eliminarTest() throws Exception{
    //Se borra por ejemplo el cliente con el código
        usuarioServicio.eliminarUsuario(1010066054);
    //Si intentamos buscar un cliente con el código del cliente borrado debemos obtener una excepción indicando que ya no existe
        Assertions.assertThrows(Exception.class, () -> usuarioServicio.obtenerUsuario(1010066054));
    }

    @Test
    @Sql("classpath:dataset.sql" )
    public void listarTest(){
        //Obtenemos la lista de todos los clientes
        List<UsuarioGetDTO> lista = usuarioServicio.listarUsuarios();
        lista.forEach(System.out::println);
        //Si en el dataset creamos 5 clientes, entonces el tamaño de la lista debe ser 5
        Assertions.assertEquals(2, lista.size());
    }

  /*  @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarTest()  {
        Usuario u = new Usuario("1004844936", "TheLemaUwU",
                "Marcela Labrador", "genesislabrador@gmail.com", "1234",
                "Cra 11a #5-45", "3234327001");
        try {
            Usuario respuesta = usuarioServicio.registarUsuario(u);
            Assertions.assertNotNull(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    public void actualizarTest() {
        try {
            Usuario u = usuarioServicio.obtenerUsuario("1010066053");
            u.setPassword("jejeje");
            usuarioServicio.actualizarUsuario(u);

            Usuario modificado = usuarioServicio.obtenerUsuario("1010066053");
            Assertions.assertEquals("jejeje", modificado.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarTest() {
        try {
            usuarioServicio.eliminarUsuario("1010066053");
            Assertions.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }

    @Test
    public void listarTest() throws Exception {
        Usuario u = new Usuario("1234", "pepo",
                "pepi Garcia", "pepito@gmail.com", "6666",
                "Cra 11a #5-42", "3234327001");

        usuarioServicio.registarUsuario(u);

        List<Usuario> lista = usuarioServicio.listarUsuario();
        lista.forEach( System.out::println );
    }

    @Test
    public void loginTest(){
        try {
            Usuario usuario = usuarioServicio.iniciarSesion("santii0628@gmail.com", "123456");
            Assertions.assertNotNull(usuario);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }*/

}
