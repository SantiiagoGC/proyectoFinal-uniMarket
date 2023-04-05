package co.edu.uniquindio.uniMarket.repositorios;
import co.edu.uniquindio.uniMarket.entidades.ProductoModerador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoModeradorRepo extends JpaRepository<ProductoModerador, Long> {
}
