package co.edu.uniquindio.uniMarket.test;

import co.edu.uniquindio.uniMarket.entidades.Favorito;
import co.edu.uniquindio.uniMarket.entidades.Producto;
import co.edu.uniquindio.uniMarket.entidades.Usuario;
import co.edu.uniquindio.uniMarket.repositorios.FavoritoRepo;
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
public class FavoritoTest {

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    @Sql("classpath:data.sql")
    public void obtenerFavoritosUsuarioTest(){

      List<Favorito> favoritos = usuarioRepo.obtenerProductosFavoritos("juanf.londonob@uqvirtual.edu.co");
      favoritos.forEach(favorito -> System.out.println(favorito));

      Assertions.assertEquals(1, favoritos.size());

    }

}
