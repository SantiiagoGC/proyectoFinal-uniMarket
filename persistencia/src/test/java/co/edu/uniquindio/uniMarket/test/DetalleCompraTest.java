package co.edu.uniquindio.uniMarket.test;

import co.edu.uniquindio.uniMarket.entidades.DetalleCompra;
import co.edu.uniquindio.uniMarket.repositorios.DetalleCompraRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DetalleCompraTest {

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    @Test
    @Sql("classpath:data.sql")
   public void buscarPorID(){
      Optional<DetalleCompra> detalleEncontrado = detalleCompraRepo.findById(1);
       System.out.println(detalleEncontrado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void buscarPorUnidades(){
        List<DetalleCompra> detalleEncontrado = detalleCompraRepo.findAllByUnidades(1);
        System.out.println(detalleEncontrado);
    }

    @Test
    @Sql("classpath:data.sql")
    public void buscarPorPrecio(){
        List<DetalleCompra> detalleEncontrado = detalleCompraRepo.findAllByPrecioProducto(12300.0);
        System.out.println(detalleEncontrado);
    }

}
