package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.UsuarioYProducto;
import co.edu.uniquindio.proyecto.entidades.Favorito;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, String> {

    List<Usuario> findAllByNombreContains(String nombre);

    Optional<Usuario> findByNombreUsuario(String username);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmailAndPassword(String email, String password);

    Page<Usuario> findAll(Pageable paginador);

    Optional<Usuario> findByNombreUsuarioAndPassword(String nombreUsuario, String password);

    @Query("select p from Usuario u, IN (u.productosFavoritos) p where u.email = :email")
    List<Favorito> obtenerProductosFavoritos(String email);

    @Query("select new co.edu.uniquindio.proyecto.dto.UsuarioYProducto(u.email, u.nombre, p) from Usuario u left join u.productosVenta p")
    List<UsuarioYProducto> listarUsuarioYProductos();

    @Query("select distinct c.usuario from Producto p join p.comentariosProducto c where p.id = :id")
    List<Usuario> listarUsuariosComentarios(Integer id);

}
