package co.edu.uniquindio.uniMarket.repositorios;

import co.edu.uniquindio.uniMarket.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepo extends JpaRepository<Imagen, Long> {
}
