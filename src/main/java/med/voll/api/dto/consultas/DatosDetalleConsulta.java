package med.voll.api.dto.consultas;

import java.time.LocalDateTime;
import med.voll.api.models.consulta.Consulta;

public record DatosDetalleConsulta(Long id, Long idPaciente, Long idMedico, LocalDateTime fecha) {
    public DatosDetalleConsulta(Consulta consulta) {
        this(consulta.getId(),consulta.getPaciente().getId(),consulta.getMedico().getId(),consulta.getFecha());
    }
}
