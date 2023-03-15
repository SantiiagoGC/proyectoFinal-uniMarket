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
public class Compra implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @Column(nullable = false)
    private double precioTotal;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @Positive
    @Column(nullable = false)
    private int cantidad;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;
}
