package co.edu.uniquindio.proyecto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductoGetDTO {

    private String nombre;
    private Integer unidades;
    private String descripcion;
    private Double precio;
    private Integer cedulaVendedor;
    private List<String> rutasImagenes;
    private Integer idCategoria;

}
