package co.edu.uniquindio.uniMarket.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Producto implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(nullable = false)
    private String id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Positive
    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false, length = 250)
    private  String descripcion;

    @Positive
    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private boolean activo;

    @Column(nullable = false)
    private LocalDate fecha_creado;

    @Future
    @Column(nullable = false)
    private LocalDate fecha_limite;

    @ManyToOne
    private Usuario vendedor;

    @OneToMany(mappedBy = "producto")
    private List<Comentario> comentarios_producto = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Detalle_Compra> detalleCompras_producto = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    private List<Imagen> imagenes_producto = new ArrayList<>();

    @OneToMany(mappedBy = "producto_categorias")
    private List<Categoria> categoria_producto = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    private List<Favorito> favorito_producto = new ArrayList<>();

    @OneToMany(mappedBy = "producto_moderador")
    private List<Producto_Moderador> moderador_producto = new ArrayList<>();
}
