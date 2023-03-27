package co.edu.uniquindio.uniMarket.repositorios;
import co.edu.uniquindio.uniMarket.entidades.Producto_Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Producto_ModeradorRepo extends JpaRepository<Producto_Moderador, Long> {
}
