package co.edu.uniquindio.proyecto.modelo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
public class UsuarioPostDTO {

    @NotBlank
    private String cedula;

    @NotBlank
    @Length(max = 20, message = "El UserName debe tener máximo 20 caracteres")
    private String nombreUsuario;

    @NotBlank
    @Length(max = 150, message = "El nombre debe tener máximo 100 caracteres")
    private String nombre;

    @NotBlank
    @Length(max = 100, message = "El correo debe tener máximo 100 caracteres")
    private String email;

    @NotBlank
    @Length(max = 50, message = "La contraseña debe tener máximo 50 caracteres")
    private String password;

    @NotBlank
    @Length(max = 100, message = "La dirección debe tener máximo 100 caracteres")
    private String direccion;

    @NotBlank
    private String fotoPerfil;

    @NotBlank
    @Length(max = 12, message = "El teléfono debe tener máximo 12 caracteres")
    private String telefono;

}
