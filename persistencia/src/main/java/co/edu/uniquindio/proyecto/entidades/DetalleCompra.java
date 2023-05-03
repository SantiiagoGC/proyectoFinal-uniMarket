package co.edu.uniquindio.proyecto.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class DetalleCompra implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Positive
    private Integer unidades;

    @Column(nullable = false)
    @Positive
    private Double precioProducto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Compra compra;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Producto producto;

    public DetalleCompra(Integer id, Integer unidades, Double precioProducto, Compra compra, Producto producto) {
        this.id = id;
        this.unidades = unidades;
        this.precioProducto = precioProducto;
        this.compra = compra;
        this.producto = producto;
    }

}
