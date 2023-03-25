package co.edu.uniquindio.uniMarket.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
public class Compra implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fecha_creacion;

    @Positive
    @Column(nullable = false)
    private Double valor_total;

    @Column(nullable = false)
    private String metodoPago;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<Detalle_Compra> compra = new ArrayList<>();
}
