package co.edu.uniquindio.proyecto.modelo.dto;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class UsuarioGetDTO {
    private String cedula;
    private String nombre;
    private String nombreUsuario;
    private String correo;
    private String direccion;
    private String telefono;
}