package co.edu.uniquindio.uniMarket.repositorios;

import co.edu.uniquindio.uniMarket.entidades.Favorito;
import co.edu.uniquindio.uniMarket.entidades.Producto;
import co.edu.uniquindio.uniMarket.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, String> {

    /*@Query("select u from Usuario u where u.nombre = :nombre")
    List<Usuario> obtenerUsuarioPorNombre(String nombre);*/

    List<Usuario> findAllByNombreContains(String nombre);
    Optional<Usuario> findByEmail(String email);

    /*@Query("select u from Usuario u where u.email = :email and u.password = :password")
    Optional<Usuario> verificarAutenticacion(String email, String password);*/
    Optional<Usuario> findByEmailAndPassword(String email, String password);

    Page<Usuario> findAll(Pageable paginador);

    Optional<Usuario> findByNombreUsuarioAndPassword(String nombreUsuario, String password);

    @Query("select p from Usuario u, IN (u.productos_favoritos) p where u.email = :email")
    List<Favorito> obtenerProductosFavoritos(String email);

}

