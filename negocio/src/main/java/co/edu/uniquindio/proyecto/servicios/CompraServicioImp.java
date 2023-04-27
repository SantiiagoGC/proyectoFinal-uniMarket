package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.excepciones.CompraNoEncontradaException;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Compra crearCompra(Compra c) throws Exception {

        Optional<Compra> encontrada = compraRepo.findById(c.getId());

        if (encontrada.isPresent())
        {
            throw new Exception("Este codigo de compra ya existe");
        }

        return compraRepo.save(c);
    }

    @Override
    public Compra actualizarCompra(Compra c) throws Exception {

        verificarId(c.getId());

        return compraRepo.save(c);

    }

    @Override
    public void eliminarCompra(Compra c) throws Exception {

        verificarId(c.getId());

        compraRepo.deleteById(c.getId());

    }

    @Override
    public Compra obtenerCompra(Integer id) throws Exception {
        return null;
    }

    @Override
    public String obtenerCorreoComprador(Integer id) throws CompraNoEncontradaException {

        verificarId(id);

        return compraRepo.obtenerCorreoComprador(id);
    }

    @Override
    public List<String> obtenerCorreosVendedores(Integer id) throws CompraNoEncontradaException {

        verificarId(id);

        return compraRepo.obtenerCorreosVendedores(id);
    }

    private void verificarId (Integer id)
    {
        Optional<Compra> buscado = compraRepo.findById(id);

        if (buscado.isEmpty())
        {
            throw  new CompraNoEncontradaException("La Id ingresada es incorrecta !");
        }
    }

}
