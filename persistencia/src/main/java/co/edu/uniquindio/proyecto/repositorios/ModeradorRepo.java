package co.edu.uniquindio.proyecto.repositorios;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeradorRepo extends JpaRepository<Moderador, String> {

    Optional<Moderador> findByNombreUsuarioAndPassword(String nombreUsuario, String password);

    Optional<Moderador> findByEmailAndPassword(String nombreUsuario, String password);

}
