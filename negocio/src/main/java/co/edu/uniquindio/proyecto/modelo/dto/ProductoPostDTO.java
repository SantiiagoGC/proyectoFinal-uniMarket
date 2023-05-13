package co.edu.uniquindio.proyecto.modelo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductoPostDTO
{

    @NotBlank
    @Length(max = 30, message = "El nombre debe tener maximo 30 caracteres")
    private String nombre;

    @NotBlank
    @Length(max = 150, message = "La descripcion debe tener maximo 150 caracteres")
    private String descripcion;

    @Positive
    private Double precio;

    @Positive
    private Integer unidades;

    private Integer cedulaVendedor;

    private List<String> rutasImagenes;

    private Integer codigoCategoria;

}
