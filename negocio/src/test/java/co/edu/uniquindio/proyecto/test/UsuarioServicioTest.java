package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
//El transactional ayuda para que no afecte la base de datos
//@Transactional
public class UsuarioServicioTest {

    @Autowired
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

    //Este es le test para logear al usuario
    @Test
    public void loginTest(){
        //Aca basicamente se crea el usuario y al mismo tiempo se logea esto en caso de que tengamos  activo el
        //transactional
        /*Usuario u = new Usuario("1004844936", "TheLemaUwU",
                "Marcela Labrador", "genesislabrador@gmail.com", "1234",
                "Cra 11a #5-45", "3234327001");
        try {
            Usuario respuesta = usuarioServicio.registarUsuario(u);
            Assertions.assertNotNull(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }*/
        //Aqui es donde se hace el login del usuario
        try {
            Usuario usuario = usuarioServicio.iniciarSesion("genesislabrador@gmail.com", "1234");
            Assertions.assertNotNull(usuario);
        } catch (Exception e) {
            Assertions.assertTrue(false, e.getMessage());
        }
    }
}
