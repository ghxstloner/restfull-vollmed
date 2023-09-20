package med.voll.api.models.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.dto.consultas.DatosAgendarConsulta;
import med.voll.api.models.consulta.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PacienteSinConsulta implements ValidadorDeConsultas{
    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosAgendarConsulta datos){
        LocalDateTime primerHorario = datos.fecha().withHour(7);
        LocalDateTime ultimoHorario = datos.fecha().withHour(18);

        Boolean pacienteConConsulta = repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);
        if(pacienteConConsulta){
            throw new ValidationException("El paciente ya tiene una consulta");
        }

    }
}
