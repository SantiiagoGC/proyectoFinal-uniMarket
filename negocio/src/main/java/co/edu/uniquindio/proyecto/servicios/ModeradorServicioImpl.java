package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Estado;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.ProductoModerador;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoModeradorRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModeradorServicioImpl implements ModeradorServicio {

    private final ModeradorRepo moderadorRepo;
    private final ProductoModeradorRepo productoModeradorRepo;

    public ModeradorServicioImpl(ModeradorRepo moderadorRepo, ProductoModeradorRepo productoModeradorRepo) {
        this.moderadorRepo = moderadorRepo;
        this.productoModeradorRepo = productoModeradorRepo;
    }


    @Override
    public Moderador iniciarSesion(String email, String password) throws Exception {
        return moderadorRepo.findByNombreUsuarioAndPassword(email, password)
                .orElseThrow( () -> new Exception("Los datos de autenticación son incorrectos") );
    }

    @Override
    public List<ProductoModerador> consultarEstado(Estado estado) throws Exception {

        if (estado == null){
            throw new Exception("El estado es nulo");

        }
        else {
            List <ProductoModerador> buscado = productoModeradorRepo.findAllByEstado(estado);
            return buscado;


        }

    }

}