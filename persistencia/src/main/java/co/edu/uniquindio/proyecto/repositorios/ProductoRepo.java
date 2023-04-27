package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.dto.ProductoValido;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    Optional<Producto> findByNombreContains(String nombre);

    @Query("select p.vendedor.nombre from Producto p where p.id = :id")
    String obtenerNombreVendedor(Integer id);

    @Query("select p.vendedor.email from Producto p where p.id = :id")
    String obtenerEmailVendedor(Integer id);

    @Query("select p.fechaLimite from Producto p where p.id = :id")
    LocalDate obtenerFechaLimite(Integer id);


    @Query("select c from Producto p join p.comentariosProducto c where p.id = :id")
    List<Comentario> obtenerComentariosProducto1(Integer id);

    @Query("select c from Comentario  c where c.producto.id = :id")
    List<Comentario> obtenerComentariosProducto2(Integer id);

    @Query("select p.nombre, c from Producto p left join p.comentariosProducto c")
    List<Object[]> listarProductosYComentarios();

    @Query("select p.nombre, p.descripcion, p.precio from Producto p where p.fechaLimite > :fechaActual")
    List<Object[]> listarProductosValidos(LocalDate fechaActual);

    List<Producto> findAllByCategoriaProducto(Categoria categoria);

    @Query("select new co.edu.uniquindio.proyecto.dto.ProductoValido(p.nombre, p.descripcion, p.precio) from Producto p where p.fechaLimite > :fechaActual")
    List<ProductoValido> listarProductosValidosDTO(LocalDate fechaActual);

    List<Producto> findAllByNombreContainingAndPrecio(String nombre, Double precio);

    List<Producto> findAllByNombreContaining(String nombre);

    List<Producto> findAllByPrecio(Double precio);


}
