package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;

import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ModeradorServicioTest {

   /* @Autowired
    private ModeradorServicio moderadorServicio;

    @Autowired
    private EstadoRepo estadoRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Test
    @Sql("classpath:data.sql")
    public void consultarEstadoTest() throws Exception {

            Optional<Estado> estado = estadoRepo.findById(1);
            List<ProductoModerador> estadosObtenidos= moderadorServicio.consultarEstado(estado.get());
            estadosObtenidos.forEach(System.out::println);
    }

    @Test
    public void loginTest(){
        try {
            Moderador moderador = moderadorServicio.iniciarSesion("santii0628@gmail.com", "123");

            System.out.println(moderador.getNombre());
            Assertions.assertNotNull(moderador);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    @Sql("classpath:data.sql")
    public void aceptarProductoTest() throws Exception {
        String mensaje = moderadorServicio.aceptarProducto(1);
        Optional<Producto> producto = productoRepo.findById(1);
        System.out.println(mensaje + "? "+producto.get().isActivo());

    }

    @Test
    @Sql("classpath:data.sql")
    public void rechazarProductoTest() throws Exception {
        String mensaje = moderadorServicio.rechazarProducto(1);
        Optional<Producto> producto = productoRepo.findById(1);
        System.out.println(mensaje + "? "+producto.get().isActivo());

    }

    @Test
    @Sql("classpath:data.sql")
    public void obtenerCorreoTest ()
    {
        try {
            String encontrado = moderadorServicio.obtenerCorreoModerador("28948972");
            System.out.println(encontrado);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
}

