package co.edu.uniquindio.proyecto.modelo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
public class ComentarioPostDTO {

    @NotBlank
    @Length(max = 230, message = "El comentario debe tener maximo 230 caracteres")
    private String mensaje;
    @NotBlank
    private Integer cedulaUsuario;
    @NotBlank
    private Integer idProducto;

}
