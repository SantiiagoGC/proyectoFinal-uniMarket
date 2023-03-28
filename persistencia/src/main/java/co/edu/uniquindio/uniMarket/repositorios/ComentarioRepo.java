package co.edu.uniquindio.uniMarket.repositorios;

import co.edu.uniquindio.uniMarket.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepo extends JpaRepository<Comentario, Long> {
     /*@Query("select c from Comentario c where c.calificacion > :calificacionMenor and c.calificacion < calificacionMayor")
    List<Comentario> listarComentariosRango(int calificacionMenor, int calificacionMayor);
    @Query("select c from Comentario c where c.calificacion between :calificacionMenor and calificacionMayor")
    List<Comentario> listarComentariosRango1(int calificacionMenor, int calificacionMayor);*/
}
