package co.edu.uniquindio.proyecto.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import org.hibernate.validator.constraints.Length;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    @Length(max = 100)
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer unidades;

    @Column(nullable = false, length = 250)
    @Length(max = 250)
    @NotBlank(message = "La descripcion del producto es obligatoria")
    @Lob
    private  String descripcion;

    @Positive
    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private boolean activo;

    @Column(nullable = false)
    private LocalDate fechaCreado;

    @Future
    @Column(nullable = false)
    private LocalDate fechaLimite;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario vendedor;

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Comentario> comentariosProducto = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<DetalleCompra> detalleComprasProducto = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Imagen> imagenesProducto = new ArrayList<>();

    @OneToMany(mappedBy = "productoCategorias")
    @ToString.Exclude
    private List<Categoria> categoriaProducto = new ArrayList<>();

    @OneToMany(mappedBy = "producto")
    @ToString.Exclude
    private List<Favorito> favoritoProducto = new ArrayList<>();

    @OneToMany(mappedBy = "productoModerador")
    @ToString.Exclude
    private List<ProductoModerador> moderadorProducto = new ArrayList<>();

    public Producto(String nombre, Integer unidades, String descripcion, Double precio, boolean activo, LocalDate fechaCreado, LocalDate fechaLimite, Usuario vendedor) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activo = activo;
        this.fechaCreado = fechaCreado;
        this.fechaLimite = fechaLimite;
        this.vendedor = vendedor;
    }

    public Producto(String nombre, Integer unidades, String descripcion, Double precio, boolean activo, Usuario vendedor) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.descripcion = descripcion;
        this.precio = precio;
        this.activo = activo;
        this.vendedor = vendedor;
    }

}
