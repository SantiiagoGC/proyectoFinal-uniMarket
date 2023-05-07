package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.modelo.dto.EmailDTO;

public interface EmailServicio {

    void enviarEmail(EmailDTO emailDTO) throws Exception;
}
