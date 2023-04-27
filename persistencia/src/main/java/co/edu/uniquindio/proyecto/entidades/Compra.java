package co.edu.uniquindio.proyecto.entidades;

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
    private Integer id;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fechaCreacion;

    @Positive
    @Column(nullable = false)
    private Double valorTotal;

    @Column(nullable = false)
    private String metodoPago;

    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "compra")
    @ToString.Exclude
    private List<DetalleCompra> compra = new ArrayList<>();

    public Compra(Integer id, LocalDate fecha_creacion, Double valor_total, String metodoPago, Usuario usuario) {
        this.id = id;
        this.fechaCreacion = fecha_creacion;
        this.valorTotal = valor_total;
        this.metodoPago = metodoPago;
        this.usuario = usuario;
    }

}
