package co.edu.uniquindio.uniMarket.repositorios;
import co.edu.uniquindio.uniMarket.entidades.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepo extends JpaRepository<Favorito, Integer> {


}
