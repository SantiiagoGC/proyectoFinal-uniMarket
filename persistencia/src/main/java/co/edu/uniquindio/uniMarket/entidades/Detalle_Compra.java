package co.edu.uniquindio.uniMarket.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
public class Detalle_Compra implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Positive
    private Integer unidades;

    @Column(nullable = false)
    @Positive
    private Double precio_producto;

    @ManyToOne
    private Compra compra;

    @ManyToOne
    private Producto producto;
}
