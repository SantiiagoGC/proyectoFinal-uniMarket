package co.edu.uniquindio.proyecto.entidades;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.Email;
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
    @Column(length = 10)
    @Length(max = 10)
    @EqualsAndHashCode.Include
    private String cedula;

    @Column(nullable = false, unique = true, length = 100)
    @Length(max = 100)
    private String nombreUsuario;

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