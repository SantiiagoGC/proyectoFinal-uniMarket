package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServicioImp implements CompraServicio {

    private final CompraRepo compraRepo;

    public CompraServicioImp(CompraRepo compraRepo) {
        this.compraRepo = compraRepo;
    }

    @Override
    public List<Compra> listarComprasUsuario(String cedula) {
            return compraRepo.findAllByUsuarioCedula(cedula);
    }

}
