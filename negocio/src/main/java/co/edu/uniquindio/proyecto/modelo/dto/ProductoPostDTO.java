package co.edu.uniquindio.proyecto.modelo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
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

    @NotBlank
    @Positive
    private Double precio;

    @NotBlank
    @Positive
    private Integer unidades;

    @NotBlank
    private String cedulaVendedor;

    private List<String> rutasImagenes;

    private List<Integer> codigosCategorias;

}
