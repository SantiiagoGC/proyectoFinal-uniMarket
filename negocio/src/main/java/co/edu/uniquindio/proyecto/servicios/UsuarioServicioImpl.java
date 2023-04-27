package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Favorito;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;

    private final ComentarioRepo comentarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, ComentarioRepo comentarioRepo) {
        this.usuarioRepo = usuarioRepo;
        this.comentarioRepo = comentarioRepo;
    }

    @Override
    public Usuario registarUsuario(Usuario u) throws Exception {

        Optional<Usuario> buscado = usuarioRepo.findById(u.getCedula());

        if( buscado.isPresent() ){
            throw new Exception("El codigo del usuario ya existe");
        }

        buscado = buscarPorEmail(u.getEmail());
        if( buscado.isPresent() ){
            throw new Exception("El email del usuario ya existe");
        }

        buscado = usuarioRepo.findByNombreUsuario(u.getNombreUsuario());
        if( buscado.isPresent() ){
            throw new Exception("El username ya esta en uso");
        }

        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(u.getCedula());
        if ( buscado.isEmpty() ){
            throw new Exception("El usuario no existe");
        }
        return usuarioRepo.save(u);
    }

    private Optional<Usuario> buscarPorEmail(String email){
        return  usuarioRepo.findByEmail(email);
    }

    @Override
    public void eliminarUsuario(String cedula) throws Exception {

        Optional<Usuario> buscado = usuarioRepo.findById(cedula);
        if( buscado.isEmpty() ){
            throw new Exception("El codigo del usuario no existe");
        }
        usuarioRepo.deleteById(cedula);

    }

    @Override
    public List<Usuario> listarUsuario() {
        return usuarioRepo.findAll();
    }

    @Override
    public List<Favorito> listaFavoritos(String email) throws Exception {

        Optional<Usuario>  buscado = buscarPorEmail(email);
        if ( buscado.isEmpty() ){
            throw new Exception("El correo no existe");
        }

        return usuarioRepo.obtenerProductosFavoritos(email);

    }

    @Override
    public Usuario obtenerUsuario(String codigo) throws Exception {

        Optional<Usuario> buscado = usuarioRepo.findById(codigo);

        if( buscado.isEmpty() ){
            throw new Exception("El usuario NO existe");
        }

        return buscado.get();
    }


    @Override
    public Usuario iniciarSesion(String email, String password) throws Exception {
        return usuarioRepo.findByEmailAndPassword(email, password)
                .orElseThrow( () -> new Exception("Los datos de autenticación son incorrectos") );
    }

}

}

