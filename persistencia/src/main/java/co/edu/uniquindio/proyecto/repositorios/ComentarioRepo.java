package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Integer> {
     /*@Query("select c from Comentario c where c.calificacion > :calificacionMenor and c.calificacion < calificacionMayor")
    List<Comentario> listarComentariosRango(int calificacionMenor, int calificacionMayor);
    @Query("select c from Comentario c where c.calificacion between :calificacionMenor and calificacionMayor")
    List<Comentario> listarComentariosRango1(int calificacionMenor, int calificacionMayor);*/

    @Query("select c from Comentario c where c.producto.id = :idProduct")
    List<Comentario> findByProduct(int idProduct);


}
