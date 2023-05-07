package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.modelo.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.modelo.dto.SesionDTO;
import co.edu.uniquindio.proyecto.modelo.dto.TokenDTO;
import co.edu.uniquindio.proyecto.modelo.dto.UsuarioPostDTO;
import co.edu.uniquindio.proyecto.servicios.SesionServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private final UsuarioServicio clienteServicio;
    private final SesionServicio sesionServicio;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO> login(@Valid @RequestBody SesionDTO loginUser) {
        TokenDTO jwtTokenDto = sesionServicio.login(loginUser);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                jwtTokenDto) );
    }

    @PostMapping("/crear_cliente")
    public ResponseEntity<MensajeDTO> registrarCliente(@Valid @RequestBody UsuarioPostDTO
                                                               cliente) throws Exception {
        clienteServicio.registarUsuario(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body( new
                MensajeDTO(HttpStatus.CREATED, false, "Cliente creado correctamente") );
    }
}