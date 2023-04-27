package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/clientes")
@AllArgsConstructor
public class ClienteController {

    @Autowired
    private final UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioServicio.listarUsuario();
    }

    @GetMapping("/{cedula}")
    public Usuario obtenerUsuario(@PathVariable String cedula) throws Exception {
        return usuarioServicio.obtenerUsuario(cedula);
    }

    @PostMapping
    public Usuario registrarUsuario(@Valid @RequestBody Usuario usuario) throws Exception {
        return usuarioServicio.registarUsuario(usuario);
    }

    @DeleteMapping("/{cedula}")
    public void eliminarUsuario(@PathVariable String cedula) throws Exception {
        usuarioServicio.eliminarUsuario(cedula);
    }

    @PutMapping
    public Usuario actualizarUsuario(@Valid @RequestBody Usuario usuario) throws Exception {
        return usuarioServicio.actualizarUsuario(usuario);
    }

    // Medio del punto 6.........
}
