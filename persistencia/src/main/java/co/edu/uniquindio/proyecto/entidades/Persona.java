package co.edu.uniquindio.proyecto.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@AllArgsConstructor
@ToString
public class Persona implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private Integer cedula;

    @Column(nullable = false, unique = true, length = 100)
    @Length(max = 100)
    private String nombreUsuario;

    private String fotoPerfil;

    @Column(nullable = false, length = 100)
    @Length(max = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 120)
    @Length(max = 120)
    @Email
    private String email;

    @Column(nullable = false, length = 100)
    @Length(max = 100)
    private  String password;

}
