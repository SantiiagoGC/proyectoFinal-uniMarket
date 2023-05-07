package co.edu.uniquindio.proyecto.controladores.excepciones;
import co.edu.uniquindio.proyecto.modelo.dto.MensajeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptions {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<MensajeDTO> badCredentialsException(BadCredentialsException e){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body( new
            MensajeDTO(HttpStatus.BAD_REQUEST, true, "Datos de autenticación incorrectos") );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO> generalException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new
                MensajeDTO(HttpStatus.INTERNAL_SERVER_ERROR, true, e.getMessage()) );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MensajeDTO> accessDeniedException(AccessDeniedException accessDeniedException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new
                MensajeDTO(HttpStatus.FORBIDDEN, true, "No se puede acceder a este recurso"));
    }

}