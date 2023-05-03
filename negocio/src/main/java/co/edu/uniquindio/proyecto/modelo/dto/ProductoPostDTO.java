package co.edu.uniquindio.proyecto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductoPostDTO {

    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer unidades;
    private String cedulaVendedor;
    private List<String> rutasImagenes;
    private List<Integer> codigosCategorias;

}
