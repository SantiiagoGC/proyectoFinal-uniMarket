package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.modelo.dto.ComentarioGetDTO;
import co.edu.uniquindio.proyecto.modelo.dto.ComentarioPostDTO;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.ComentarioServicio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComentarioServicioImpl  implements ComentarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final ProductoRepo productoRepo;
    private final ComentarioRepo comentarioRepo;

    public ComentarioServicioImpl(UsuarioRepo usuarioRepo, ProductoRepo productoRepo, ComentarioRepo comentarioRepo) {
        this.usuarioRepo = usuarioRepo;
        this.productoRepo = productoRepo;
        this.comentarioRepo = comentarioRepo;
    }

    @Override
    public int crearComentario(ComentarioPostDTO comentario) throws Exception {
        if(comentario != null){
            Comentario comentarioBack = new Comentario();
            comentarioBack.setMensaje( comentario.getMensaje() );
            comentarioBack.setFechaCreacion( LocalDate.now() );
            Optional<Usuario> encontradoU = usuarioRepo.findById(comentario.getCedulaUsuario());
            encontradoU.ifPresent(comentarioBack::setUsuario);
            Optional<Producto> encontradoP = productoRepo.findById(comentario.getIdProducto());
            encontradoP.ifPresent(comentarioBack::setProducto);
            comentarioRepo.save(comentarioBack);
            return comentarioBack.getId();
        }else {
            throw new Exception("No hay comentario");
        }
    }

    @Override
    public List<ComentarioGetDTO> listarComentarioProducto(int idProduct) {

        List<Comentario> comentariosProducto = comentarioRepo.findByProduct(idProduct);
        List<ComentarioGetDTO> comentariosGetDTOS = new ArrayList<>();

        for (Comentario comentario: comentariosProducto) {
            comentariosGetDTOS.add(convertir(comentario));
        }

        return comentariosGetDTOS;
    }

    private ComentarioGetDTO convertir(Comentario comentario) {

        return new ComentarioGetDTO(
                comentario.getId(),
                comentario.getFechaCreacion(),
                comentario.getMensaje(),
                comentario.getProducto().getId(),
                comentario.getUsuario().getCedula());
    }
}
