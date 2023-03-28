package co.edu.uniquindio.uniMarket.repositorios;

import co.edu.uniquindio.uniMarket.entidades.Detalle_Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Detalle_CompraRepo extends JpaRepository<Detalle_Compra, Integer> {
    List<Detalle_Compra> findAllByUnidades(Integer unidades);
    List<Detalle_Compra> findAllByPrecioProducto(Double precioProducto);
    Optional<Detalle_Compra> findById( Integer id ) ;
}
