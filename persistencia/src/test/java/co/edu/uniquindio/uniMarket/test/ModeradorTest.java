package co.edu.uniquindio.uniMarket.test;
import co.edu.uniquindio.uniMarket.entidades.Moderador;
import co.edu.uniquindio.uniMarket.repositorios.ModeradorRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ModeradorTest {

    @Autowired
    private ModeradorRepo moderadorRepo;

    @Test
    @Sql("classpath:data.sql")
    public void validarDatos(){

        Optional<Moderador> moderador = moderadorRepo.findByNombreUsuarioAndPassword("CarmenB", "123456");

        if(moderador.isPresent()){
            System.out.println("Los datos coinciden");
        }else{
            System.out.println("Los datos no coinciden");
        }
    }

}
