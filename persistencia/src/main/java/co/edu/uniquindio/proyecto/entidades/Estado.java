package co.edu.uniquindio.proyecto.entidades;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.List;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Estado implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 80)
    private String nombre;

    @OneToMany(mappedBy = "estado")
    @ToString.Exclude
    private List<ProductoModerador> estadoProducto;

}
