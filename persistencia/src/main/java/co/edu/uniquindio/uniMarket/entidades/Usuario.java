package co.edu.uniquindio.uniMarket.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {
    @Column(nullable = false)
    private  String direccion;

    @Column(nullable = false, length = 10)
    private  String telefono;

    @ToString.Exclude
    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "vendedor")
    private List<Producto> productos_venta = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "usuario")
    private List<Compra> producto_compras = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "usuario")
    private List<Favorito>  productos_favoritos = new ArrayList<>();


    public Usuario(String cedula, String nombre, String email, String password, String direccion, String telefono) {
        super(cedula, nombre, email, password);
        this.direccion = direccion;
        this.telefono = telefono;
    }
}
