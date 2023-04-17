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
        return null;
    }

    @GetMapping("/{cedula}")
    public Usuario obtenerUsuario(@PathVariable String cedula){
        return null;
    }

    @PostMapping
    public int registrarUsuario(@Valid @RequestBody Usuario usuarioPostDTO){
        return 0;
    }

    @DeleteMapping("/{cedula}")
    public void eliminarUsuario(@PathVariable String cedula){

    }

    @PutMapping("/{cedula}")
    public Usuario actualizarUsuario(@PathVariable String cedula, @Valid @RequestBody Usuario usuario){
        return null;
    }

    // Medio del punto 6.........
}
