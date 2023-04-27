package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Estado;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.ProductoModerador;
import co.edu.uniquindio.proyecto.repositorios.EstadoRepo;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ModeradorServicioTest {

    @Autowired
    private ModeradorServicio moderadorServicio;
    @Autowired
    private EstadoRepo estadoRepo;

    @Test
    @Sql("classpath:data.sql")
    public void consultarEstadoTest() throws Exception {


            Optional<Estado> estado = estadoRepo.findById(1);
            List<ProductoModerador> estadosObtenidos= moderadorServicio.consultarEstado(estado.get());
            estadosObtenidos.forEach(System.out::println);

    }

    @Test
    public void loginTest(){
        try {
            Moderador moderador = moderadorServicio.iniciarSesion("santii0628@gmail.com", "123");
            Assertions.assertNotNull(moderador);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
}