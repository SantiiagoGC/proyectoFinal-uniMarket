package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.modelo.dto.CategoriaGetDTO;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.CategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

    @Autowired
    CategoriaRepo categoriaRepo;

    @Override
    public List<CategoriaGetDTO> listarCategorias() {

        List<Categoria> categoriaList = categoriaRepo.findAll();
        List<CategoriaGetDTO> categoriaGetDTOList = new ArrayList<>();

        for (Categoria categoria: categoriaList) {
            categoriaGetDTOList.add(convertir(categoria));
        }

        return categoriaGetDTOList;
    }

    private CategoriaGetDTO convertir(Categoria categoria) {

        return new CategoriaGetDTO(
                categoria.getCodigo(),
                categoria.getNombre());

    }

}
