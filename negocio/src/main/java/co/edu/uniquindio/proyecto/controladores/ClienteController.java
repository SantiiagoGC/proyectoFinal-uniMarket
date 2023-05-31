package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.modelo.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.dto.UsuarioPostDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

    @Autowired
    private final UsuarioServicio usuarioServicio;

    @PostMapping
    public ResponseEntity<MensajeDTO> registrar(@Valid @RequestBody UsuarioPostDTO cliente) throws Exception{
        usuarioServicio.registarUsuario(cliente);
        return ResponseEntity.status(201).body(new MensajeDTO(HttpStatus.CREATED, false,
                "Usuario creado correctamente"));
    }

    @Operation(summary = "Obtener Usuario", description = "Se consulta el usuario dado su código (cedula)")
    @GetMapping("/obtener/{cedula}")
    public ResponseEntity<MensajeDTO> obtenerUsuario(@PathVariable int cedula) throws Exception{
        return ResponseEntity.status(200).body( new MensajeDTO(HttpStatus.OK, false,
                usuarioServicio.obtenerUsuario(cedula)));
    }

    @Operation(summary = "Eliminar Usuario", description = "Se elimina el usuario dado su código (cedula)")
    @DeleteMapping("/eliminar/{cedula}")
    public ResponseEntity<MensajeDTO> eliminarUsuario(@PathVariable int cedula) throws Exception{
        usuarioServicio.eliminarUsuario(cedula);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                "Eliminado correctamente" ) );
    }

    @Operation(summary = "Actualizar Usuario", description = "Se actualiza el usuario dado su código (cedula), y nuevos datos")
    @PutMapping("/actualizar/{cedula}")
    public ResponseEntity<MensajeDTO> actualizarUsuario(@PathVariable int cedula, @Valid @RequestBody
    UsuarioPostDTO cliente) throws Exception{
        usuarioServicio.actualizarUsuario(cedula, cliente);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                "Actualizado correctamente" ) );
    }

    @Operation(summary = "Listar favoritos usuario Usuario", description = "Se consulta el usuario dado su email")
    @GetMapping("/list_fav/{email}")
    public ResponseEntity<MensajeDTO> listaFavoritosUsuario(@PathVariable String email) throws Exception{
        return ResponseEntity.status(200).body( new MensajeDTO(HttpStatus.OK, false,
                usuarioServicio.listaFavoritos(email)));
    }
}
