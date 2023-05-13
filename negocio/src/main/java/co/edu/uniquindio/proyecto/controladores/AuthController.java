package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.modelo.dto.*;
import co.edu.uniquindio.proyecto.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.SesionServicio;
import co.edu.uniquindio.proyecto.servicios.interfaces.UsuarioServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@SecurityRequirement(name = "bearerAuth")
@AllArgsConstructor
public class AuthController {

    private final UsuarioServicio clienteServicio;
    private final SesionServicio sesionServicio;
    private final ModeradorServicio moderadorServicio;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO> login(@Valid @RequestBody SesionDTO loginUser) {
        TokenDTO jwtTokenDto = sesionServicio.login(loginUser);
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                jwtTokenDto) );
    }

    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO> registrarCliente(@Valid @RequestBody UsuarioPostDTO cliente) throws Exception {
        clienteServicio.registarUsuario(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body( new
                MensajeDTO(HttpStatus.CREATED, false, "Cliente creado correctamente") );
    }

    @PostMapping("/registroAdmin")
    public ResponseEntity<MensajeDTO> registerAdmin(@Valid @RequestBody AdminPostDTO adminPostDTO) throws Exception {
        moderadorServicio.registrarAdmin(adminPostDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,false, "Admin registrado correctamente"));
    }

}