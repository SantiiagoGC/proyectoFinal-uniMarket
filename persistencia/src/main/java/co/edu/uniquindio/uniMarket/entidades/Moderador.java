package co.edu.uniquindio.uniMarket.entidades;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Moderador extends Persona implements Serializable {
    @ManyToMany (mappedBy = "productosModerador")
    private List<Producto> productos;
}
