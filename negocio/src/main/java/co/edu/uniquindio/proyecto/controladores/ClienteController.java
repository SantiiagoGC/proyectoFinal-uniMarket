package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.modelo.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.dto.UsuarioPostDTO;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clientes")
@AllArgsConstructor
public class ClienteController {

    @Autowired
    private final UsuarioServicio usuarioServicio;
/*
    @GetMapping
    public List<UsuarioGetDTO> listarUsuarios(){
        return usuarioServicio.listarUsuarios();
    }

    @PostMapping
    public String registrarUsuario(@Valid @RequestBody UsuarioPostDTO usuario) throws Exception {
        return usuarioServicio.registarUsuario(usuario);
    }

    @GetMapping("/{cedula}")
    public UsuarioGetDTO obtenerUsuario(@PathVariable String cedula) throws Exception {
        return usuarioServicio.obtenerUsuario(cedula);
    }

    @DeleteMapping("/{cedula}")
    public void eliminarUsuario(@PathVariable String cedula) throws Exception {
        usuarioServicio.eliminarUsuario(cedula);
    }

    @PutMapping("/{cedula}")
    public UsuarioGetDTO actualizarUsuario(@PathVariable String cedula, @Valid @RequestBody UsuarioPostDTO usuario)
            throws Exception {
        return usuarioServicio.actualizarUsuario(cedula, usuario);
    }*/

    @PostMapping
    public ResponseEntity<MensajeDTO> registrar(@Valid @RequestBody UsuarioPostDTO cliente) throws Exception{        usuarioServicio.registarUsuario(cliente);
        return ResponseEntity.status(201).body(new MensajeDTO(HttpStatus.CREATED, false,
                "Usuario creado correctamente"));
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<MensajeDTO> obtener(@PathVariable int cedula) throws Exception{
        return ResponseEntity.status(200).body( new MensajeDTO(HttpStatus.OK, false,
                usuarioServicio.obtenerUsuario(cedula)));
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<MensajeDTO> eliminar(@PathVariable int cedula) throws Exception{
        usuarioServicio.eliminarUsuario(cedula);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                "Eliminado correctamente" ) );
    }

    @PutMapping("/{cedula}")
    public ResponseEntity<MensajeDTO> actualizar(@PathVariable int cedula, @Valid @RequestBody
    UsuarioPostDTO cliente) throws Exception{
        usuarioServicio.actualizarUsuario(cedula, cliente);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                "Actualizado correctamente" ) );
    }

}
