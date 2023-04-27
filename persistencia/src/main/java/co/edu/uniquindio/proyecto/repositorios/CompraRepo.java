package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {

    List<Compra> findAllByUsuarioCedula (String cedula);

    @Query("select c.usuario.email from Compra c where c.id = :id")
    String obtenerCorreoComprador (Integer id);

    @Query("select p.vendedor.email from Compra c join c.compra v join v.producto p where c.id = :id")
    List<String> obtenerCorreosVendedores (Integer id);
}
