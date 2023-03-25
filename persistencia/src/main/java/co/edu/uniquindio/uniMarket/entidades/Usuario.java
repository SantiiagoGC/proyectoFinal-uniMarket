package co.edu.uniquindio.uniMarket.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Usuario extends Persona implements Serializable {
    @Column(nullable = false)
    private  String direccion;

    @Column(nullable = false)
    private  String telefono;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "vendedor")
    private List<Producto> productos_venta = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Compra> producto_compras = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Favorito>  productos_favoritos = new ArrayList<>();
}
