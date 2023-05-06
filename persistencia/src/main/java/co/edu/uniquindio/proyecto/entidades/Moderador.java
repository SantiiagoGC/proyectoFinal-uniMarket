package co.edu.uniquindio.proyecto.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;


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

    public Moderador(String cedula, String nombreUsuario, String fotoPefil, String nombre, String email, String password) {
        super(cedula, nombreUsuario, fotoPefil, nombre, email, password);
    }

}
