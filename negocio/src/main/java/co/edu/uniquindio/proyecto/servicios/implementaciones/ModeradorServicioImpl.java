package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.modelo.dto.AdminPostDTO;
import co.edu.uniquindio.proyecto.modelo.dto.EmailDTO;
import co.edu.uniquindio.proyecto.modelo.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoModeradorRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;
import org.springframework.stereotype.Service;
import java.util.List;

import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;

import java.util.Optional;


@Service
public class ModeradorServicioImpl implements ModeradorServicio {

    private final ModeradorRepo moderadorRepo;
    private final ProductoRepo productoRepo;
    private final ProductoModeradorRepo productoModeradorRepo;

    public ModeradorServicioImpl(ModeradorRepo moderadorRepo, ProductoRepo productoRepo, ProductoModeradorRepo productoModeradorRepo) {
        this.moderadorRepo = moderadorRepo;
        this.productoRepo = productoRepo;
        this.productoModeradorRepo = productoModeradorRepo;
    }


    @Override
    public Moderador iniciarSesion(String email, String password) throws Exception {

        return moderadorRepo.findByEmailAndPassword(email, password)
                .orElseThrow( () -> new Exception("Los datos de autenticación son incorrectos") );
    }

    @Override
    public Integer registrarAdmin(AdminPostDTO adminPostDTO) throws Exception {

        Optional<Moderador> buscado = moderadorRepo.findById(adminPostDTO.getCedula());

        if( buscado.isPresent() ){
            throw new Exception("El codigo del moderador ya existe");
        }

        buscado = buscarPorEmail(adminPostDTO.getEmail());
        if( buscado.isPresent() ){
            throw new Exception("El email del moderador ya existe");
        }

        buscado = moderadorRepo.findByNombreUsuario(adminPostDTO.getNombreUsuario());
        if( buscado.isPresent() ){
            throw new Exception("El username ya esta en uso");
        }

        Moderador moderador = new Moderador();
        moderador.setEmail( adminPostDTO.getEmail() );
        moderador.setCedula( adminPostDTO.getCedula() );
        moderador.setPassword( adminPostDTO.getPassword() );
        moderador.setNombre( adminPostDTO.getNombre() );
        moderador.setFotoPerfil( adminPostDTO.getFotoPerfil() );
        moderador.setNombreUsuario( adminPostDTO.getNombreUsuario() );

        Moderador guardado = moderadorRepo.save(moderador);

        //emailServicio.enviarEmail(new EmailDTO("Cuenta creada", "Cuenta abierta en UniMarket.", usuario.getEmail()));

        return guardado.getCedula();
    }

    private Optional<Moderador> buscarPorEmail(String email) {
        return moderadorRepo.findByEmail(email);
    }

    /*
    Convertir de adentro hacia afuera
     */
    private AdminPostDTO convertir(Moderador moderador){
        return null;
    }

    @Override
    public List<ProductoModerador> consultarEstado(Estado estado) throws Exception {

        if (estado == null){
            throw new Exception("El estado es nulo");

        }
        else {
            return productoModeradorRepo.findAllByEstado(estado);
        }

    }

    public String aceptarProducto(Integer codigo) throws Exception {
        Optional<Producto> productoBuscado = productoRepo.findById(codigo);

        if ( productoBuscado.isEmpty() ){
            throw new Exception("No se ha podido encontrar el producto") ;
        } else {
            Producto productoEncontrado = productoBuscado.get();
            productoEncontrado.setActivo(true);
            productoRepo.save(productoEncontrado);
            return "Se ha aceptado el producto";
        }
    }

    @Override
    public String rechazarProducto(Integer codigo) throws Exception {
        Optional<Producto> productoBuscado = productoRepo.findById(codigo);

        if ( productoBuscado.isEmpty() ){
            throw new Exception("No se ha podido encontrar el producto") ;
        } else {
            Producto productoEncontrado = productoBuscado.get();
            productoEncontrado.setActivo(false);
            productoRepo.save(productoEncontrado);
            return "Se ha aceptado el producto";
        }
    }

    @Override
    public Integer obtenerCorreoModerador(Integer cedula) throws Exception {
        Optional<Moderador> encotrado = moderadorRepo.findById(cedula);

        if (encotrado.isEmpty())
        {
            throw new Exception("Cedula no encontrada !");
        }

        return moderadorRepo.obtenerCorreoModerador(cedula);
    }


}

