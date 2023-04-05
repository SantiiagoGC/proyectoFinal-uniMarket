package co.edu.uniquindio.uniMarket.entidades;

import lombok.*;

import javax.persistence.Entity;
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
    @ToString.Exclude
    private List<ProductoModerador> productosModerador;

    public Moderador(String cedula, String nombreUsuario, String nombre, String email, String password) {
        super(cedula, nombreUsuario, nombre, email, password);
    }

}
