package co.edu.uniquindio.uniMarket.entidades;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Persona implements Serializable {

    @Id
    @Column(length = 10)
    @EqualsAndHashCode.Include
    private String cedula;
    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(nullable = false, length = 10)
    private Integer numTelefono;

    @Column(nullable = false, length = 100)
    private String direccion;

    @Column(nullable = false, unique = true)
    private  String contrasenia;
}
