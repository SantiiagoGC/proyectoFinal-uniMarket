package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Estado;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.ProductoModerador;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoModeradorRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import co.edu.uniquindio.proyecto.entidades.Producto;
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
    public String obtenerCorreoModerador(String cedula) throws Exception {
        Optional<Moderador> encotrado = moderadorRepo.findById(cedula);

        if (encotrado.isEmpty())
        {
            throw new Exception("Cedula no encontrada !");
        }

        return moderadorRepo.obtenerCorreoModerador(cedula);
    }

}

