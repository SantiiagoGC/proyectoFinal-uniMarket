package co.edu.uniquindio.proyecto.repositorios;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModeradorRepo extends JpaRepository<Moderador, Integer> {

    Optional<Moderador> findByNombreUsuarioAndPassword(String nombreUsuario, String password);

    Optional<Moderador> findByEmailAndPassword(String nombreUsuario, String password);

    @Query("select m.email from Moderador m where m.cedula = :cedula")
    Integer obtenerCorreoModerador(Integer cedula);

    Optional<Moderador> findByEmail(String email);

}
