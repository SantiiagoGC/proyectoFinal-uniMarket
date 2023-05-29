package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.modelo.dto.SesionDTO;
import co.edu.uniquindio.proyecto.modelo.dto.TokenDTO;

public interface SesionServicio {

    TokenDTO login(SesionDTO sesionDTO);


}
