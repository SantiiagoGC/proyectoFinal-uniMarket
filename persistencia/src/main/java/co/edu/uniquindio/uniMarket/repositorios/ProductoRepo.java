package co.edu.uniquindio.uniMarket.repositorios;

import co.edu.uniquindio.uniMarket.dto.ProductoValido;
import co.edu.uniquindio.uniMarket.entidades.Comentario;
import co.edu.uniquindio.uniMarket.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, String> {

    Optional<Producto> findByNombreContains(String nombre);

    @Query("select p.vendedor.nombre from Producto p where p.id = :id")
    String obtenerNombreVendedor(String id);

    @Query("select c from Producto p join p.comentariosProducto c where p.id = :id")
    List<Comentario> obtenerComentariosProducto1(String id);

    @Query("select c from Comentario  c where c.producto.id = :id")
    List<Comentario> obtenerComentariosProducto2(String id);

    @Query("select p.nombre, c from Producto p left join p.comentariosProducto c")
    List<Object[]> listarProductosYComentarios();

    @Query("select p.nombre, p.descripcion, p.precio from Producto p where p.fechaLimite > :fechaActual")
    List<Object[]> listarProductosValidos(LocalDate fechaActual);

    @Query("select new co.edu.uniquindio.uniMarket.dto.ProductoValido(p.nombre, p.descripcion, p.precio) from Producto p where p.fechaLimite > :fechaActual")
    List<ProductoValido> listarProductosValidosDTO(LocalDate fechaActual);

}
