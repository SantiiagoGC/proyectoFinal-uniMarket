package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Favorito;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.modelo.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.modelo.dto.UsuarioPostDTO;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;

    private final PasswordEncoder passwordEncoder;

    private final EmailServicioImpl emailServicio;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo, PasswordEncoder passwordEncoder, EmailServicioImpl emailServicio) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
        this.emailServicio = emailServicio;
    }

    /*
    Implementado el Registrar usuarios con DTO.
     */
    @Override
    public Integer registarUsuario(UsuarioPostDTO u) throws Exception {

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

        Usuario usuario = new Usuario();
        usuario.setFotoPerfil( u.getFotoPerfil() );
        usuario.setCedula( u.getCedula() );
        usuario.setNombre( u.getNombre() );
        usuario.setNombreUsuario( u.getNombreUsuario() );
        usuario.setEmail( u.getEmail() );
        usuario.setPassword( passwordEncoder.encode( u.getPassword()) );
        usuario.setDireccion( u.getDireccion() );
        usuario.setTelefono( u.getTelefono() );

        Usuario guardado = usuarioRepo.save(usuario);
        //emailServicio.enviarEmail(new EmailDTO("Cuenta creada", "Felicidades Crack", usuario.getEmail()));

        return guardado.getCedula();
    }

    /*@Override
    public String actualizarUsuario(String cedula, UsuarioGetDTO u) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(cedula);
        if ( buscado.isEmpty() ){
            throw new Exception("El usuario no existe");
        }
        Usuario guardado = buscado.get();

        guardado.setCedula( u.getCedula() );
        guardado.setNombre( u.getNombre() );
        guardado.setNombreUsuario( u.getNombreUsuario() );
        guardado.setEmail( u.getCorreo() );
        guardado.setDireccion( u.getDireccion() );
        guardado.setTelefono( u.getTelefono() );

        convertir( usuarioRepo.save(guardado) );

        return  guardado.getCedula();
    }*/

    /*
    Implementado el Actualizar usuario con DTO
     */
    @Override
    public UsuarioGetDTO actualizarUsuario(Integer cedula, UsuarioPostDTO u) throws Exception {
        Optional<Usuario> registro = usuarioRepo.findById(cedula);

        if (registro.isEmpty()) {
            throw new Exception("El cliente no existe");
        }

        Usuario guardado = registro.get();

        guardado.setNombre( u.getNombre() );
        guardado.setEmail( u.getEmail() );
        guardado.setDireccion( u.getDireccion() );
        guardado.setTelefono( u.getTelefono() );
        guardado.setFotoPerfil( u.getFotoPerfil() );

        return convertir(usuarioRepo.save(guardado));

    }

    private Optional<Usuario> buscarPorEmail(String email){
        return  usuarioRepo.findByEmail(email);
    }

    /*
    Implementado No Necesita DTO
     */
    @Override
    public void eliminarUsuario(Integer cedula) throws Exception {

        Optional<Usuario> buscado = usuarioRepo.findById(cedula);
        if( buscado.isEmpty() ){
            throw new Exception("El codigo del usuario no existe");
        }
        usuarioRepo.deleteById(cedula);

    }

    /*
    Listar con DTO implementado
     */
    @Override
    public List<UsuarioGetDTO> listarUsuarios() {
        return convertirLista( usuarioRepo.findAll() );
    }

    @Override
    public List<Favorito> listaFavoritos(String email) throws Exception {

        Optional<Usuario>  buscado = buscarPorEmail(email);
        if ( buscado.isEmpty() ){
            throw new Exception("El correo no existe");
        }

        return usuarioRepo.obtenerProductosFavoritos(email);

    }

    /*
    Consultar con DTO implementado
     */
    @Override
    public UsuarioGetDTO obtenerUsuario(Integer codigo) throws Exception {

        Optional<Usuario> buscado = usuarioRepo.findById(codigo);

        if( buscado.isEmpty() ){
            throw new Exception("El usuario NO existe");
        }

        Usuario aux = buscado.get();

        return new UsuarioGetDTO(
            aux.getCedula(),
                aux.getNombre(),
                aux.getNombreUsuario(),
                aux.getEmail(),
                aux.getDireccion(),
                aux.getTelefono(),
                aux.getFotoPerfil()
        );
    }


    /*
    No necesita DTO
     */
    @Override
    public Usuario iniciarSesion(String email, String password) throws Exception {
        return usuarioRepo.findByEmailAndPassword(email, password)
                .orElseThrow( () -> new Exception("Los datos de autenticación son incorrectos") );
    }

    /*
    Convertir de adentro hacia afuera
     */
    private  UsuarioGetDTO convertir(Usuario usuario){
        return new UsuarioGetDTO(
                usuario.getCedula(),
                usuario.getNombre(),
                usuario.getNombreUsuario(),
                usuario.getEmail(),
                usuario.getDireccion(),
                usuario.getTelefono(),
                usuario.getFotoPerfil());
    }

    /*
    Convertir listas
     */
    private List<UsuarioGetDTO> convertirLista(List<Usuario> lista){
        List<UsuarioGetDTO> respuesta = new ArrayList<>();
        for(Usuario c : lista){
            respuesta.add( convertir(c) );
        }
        return respuesta;
    }


}


