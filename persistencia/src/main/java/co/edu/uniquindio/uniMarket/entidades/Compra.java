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
    @Column(length = 50)
    @EqualsAndHashCode.Include
    private String id;

    @Positive
    @Column(nullable = false)
    private double precioTotal;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;
}
