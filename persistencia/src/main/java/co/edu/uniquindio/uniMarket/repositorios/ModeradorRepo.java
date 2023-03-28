package co.edu.uniquindio.uniMarket.repositorios;
import co.edu.uniquindio.uniMarket.entidades.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeradorRepo extends JpaRepository<Moderador, String> {
    Optional<Moderador> findByNombreUsuarioAndPassword(String nombreUsuario, String password);
}
