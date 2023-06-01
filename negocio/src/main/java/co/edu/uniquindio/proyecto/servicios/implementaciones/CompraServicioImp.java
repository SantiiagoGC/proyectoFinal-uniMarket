package co.edu.uniquindio.proyecto.servicios.implementaciones;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.excepciones.CompraNoEncontradaException;
import co.edu.uniquindio.proyecto.modelo.dto.CompraPostDTO;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.CompraServicio;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompraServicioImp implements CompraServicio {

    private final CompraRepo compraRepo;

    private final ProductoRepo productoRepo;

    private final DetalleCompraRepo detalleCompraRepo;

    private final UsuarioRepo usuarioRepo;

    public CompraServicioImp(CompraRepo compraRepo, ProductoRepo productoRepo, DetalleCompraRepo detalleCompraRepo, UsuarioRepo usuarioRepo) {
        this.compraRepo = compraRepo;
        this.productoRepo = productoRepo;
        this.detalleCompraRepo = detalleCompraRepo;
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public List<Compra> listarComprasUsuario(String cedula) {
            return compraRepo.findAllByUsuarioCedula(cedula);
    }

    @Override
    public Compra crearCompra(Integer unidades, Integer codigoProducto, CompraPostDTO compraPostDTO) throws Exception {

        Optional<Producto> producto = productoRepo.findById(codigoProducto);
        Optional<Usuario> usuario = usuarioRepo.findById(compraPostDTO.getCedulaUsuario());

        if (producto.isEmpty()){
            throw new Exception("El producto no existe");
        }

        if (usuario.isEmpty()){
            throw new Exception("El usuario no existe");
        }

        if (producto.get().getUnidades().equals(0)) {
            throw new Exception("No hay unidades disponibles");
        }

        Compra compra = new Compra();

        compra.setFechaCreacion(LocalDate.now());
        compra.setMetodoPago(compraPostDTO.getMetodoPago());
        compra.setValorTotal(compraPostDTO.getValorTotal());
        compra.setUsuario(usuario.get());

        DetalleCompra detalleCompra = new DetalleCompra();
        detalleCompra.setCompra(compra);
        detalleCompra.setProducto(producto.get());
        detalleCompra.setUnidades(unidades);
        detalleCompra.setPrecioProducto(producto.get().getPrecio());

        int unidadesP = producto.get().getUnidades();
        producto.get().setUnidades(unidadesP-unidades);

        usuario.get().getProductoCompras().add(compra);

        usuarioRepo.save(usuario.get());
        productoRepo.save(producto.get());
        detalleCompraRepo.save(detalleCompra);
        return compraRepo.save(compra);
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
