package co.edu.uniquindio.uniMarket.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Moderador extends Persona implements Serializable {
    @OneToMany(mappedBy = "moderador")
    private List<Producto_Moderador> productos_moderador;


}
