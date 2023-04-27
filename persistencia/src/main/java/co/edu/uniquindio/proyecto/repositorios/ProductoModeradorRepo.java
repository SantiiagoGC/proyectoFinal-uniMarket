package co.edu.uniquindio.proyecto.repositorios;
import co.edu.uniquindio.proyecto.entidades.Estado;
import co.edu.uniquindio.proyecto.entidades.ProductoModerador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoModeradorRepo extends JpaRepository<ProductoModerador, Integer> {
    List<ProductoModerador> findAllByEstado(Estado estado);
}
