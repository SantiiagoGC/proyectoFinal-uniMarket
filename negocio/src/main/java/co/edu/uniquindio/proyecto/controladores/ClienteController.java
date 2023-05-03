package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.modelo.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.modelo.dto.UsuarioPostDTO;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/clientes")
@AllArgsConstructor
public class ClienteController {

    @Autowired
    private final UsuarioServicio usuarioServicio;

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

    /*@PutMapping("/{cedula}")
    public UsuarioGetDTO actualizarUsuario(@PathVariable String cedula, @Valid @RequestBody UsuarioPostDTO usuario)
            throws Exception {
        return usuarioServicio.actualizarUsuario(cedula, usuario);
    }*/

}
