package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class CompraServicioTest {
/*
    @Autowired
    private CompraRepo compraRepo;
    @Autowired
    private CompraServicio compraServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Sql("classpath:data.sql")
    @Test
    public void listarComprasUsuario () {

        List<Compra> comprasList = compraRepo.findAllByUsuarioCedula("1007531125");
        comprasList.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:data.sql")
    public void crearCompraTest ()
    {
        UsuarioGetDTO usuario;
        try {
            usuario = usuarioServicio.obtenerUsuario("1010066053");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        LocalDate localDateCreado = LocalDate.of(2024,1,2);

        Compra c = new Compra(5,localDateCreado, 200.0, "paypal", usuario);

        try {
            compraServicio.crearCompra(c);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void actualizarCompraTest ()
    {
        UsuarioGetDTO usuario;
        try {
            usuario = usuarioServicio.obtenerUsuario("1010066053");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        LocalDate localDateCreado = LocalDate.of(2024,1,2);

        Compra c = new Compra(1,localDateCreado, 200.0, "paypal", usuario);

        try {
            compraServicio.actualizarCompra(c);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void eliminarCompraTest ()
    {

        Usuario usuario;
        try {
            usuario = usuarioServicio.obtenerUsuario("1010066053");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        LocalDate localDateCreado = LocalDate.of(2024,1,2);

        Compra c = new Compra(1,localDateCreado, 200.0, "paypal", usuario);

        try {
            compraServicio.eliminarCompra(c);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void obtenerCorreosTest () throws Exception {

        Integer idCompra = 1;

        String correoComprador;
        List<String> correosVendedores;
        try {
            correoComprador = compraServicio.obtenerCorreoComprador(idCompra);
            correosVendedores = compraServicio.obtenerCorreosVendedores(idCompra);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals("juanf.londonob@uqvirtual.edu.co", correoComprador);
        Assertions.assertEquals(1, correosVendedores.size());

    }*/
}
