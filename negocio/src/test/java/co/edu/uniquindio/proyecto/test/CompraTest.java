package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;

    @Sql("classpath:data.sql")
    @Test
    public void listarComprasUsuario () {

        List<Compra> comprasList = compraRepo.findAllByUsuarioCedula("1007531125");
        comprasList.forEach(System.out::println);

    }
}
