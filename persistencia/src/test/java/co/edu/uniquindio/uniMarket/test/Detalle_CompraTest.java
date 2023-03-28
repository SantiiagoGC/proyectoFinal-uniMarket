package co.edu.uniquindio.uniMarket.test;

import co.edu.uniquindio.uniMarket.entidades.Detalle_Compra;
import co.edu.uniquindio.uniMarket.entidades.Usuario;
import co.edu.uniquindio.uniMarket.repositorios.Detalle_CompraRepo;
import co.edu.uniquindio.uniMarket.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class Detalle_CompraTest {

    @Autowired
    private Detalle_CompraRepo detalleCompraRepo;

    @Test
    @Sql("classpath:data.sql")
   public void buscarPorID(){
      Optional<Detalle_Compra> detalleEncontrado = detalleCompraRepo.findById(1);
       System.out.println(detalleEncontrado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void buscarPorUnidades(){
        List<Detalle_Compra> detalleEncontrado = detalleCompraRepo.findAllByUnidades(1);
        System.out.println(detalleEncontrado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void buscarPorPrecio(){
        List<Detalle_Compra> detalleEncontrado = detalleCompraRepo.findAllByPrecioProducto(12300.0);
        System.out.println(detalleEncontrado);
    }

}
