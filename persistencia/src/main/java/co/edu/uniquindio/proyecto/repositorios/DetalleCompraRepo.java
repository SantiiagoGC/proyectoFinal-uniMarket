package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DetalleCompraRepo extends JpaRepository<DetalleCompra, Integer> {

    List<DetalleCompra> findAllByUnidades(Integer unidades);

    List<DetalleCompra> findAllByPrecioProducto(Double precioProducto);

    Optional<DetalleCompra> findById(Integer id ) ;

}
