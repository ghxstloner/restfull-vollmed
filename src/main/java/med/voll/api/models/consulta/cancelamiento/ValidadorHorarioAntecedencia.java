package med.voll.api.models.consulta.cancelamiento;

import jakarta.validation.ValidationException;
import med.voll.api.dto.consultas.DatosCancelamientoConsulta;
import med.voll.api.models.consulta.Consulta;
import med.voll.api.models.consulta.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAtecedenciaCancelamiento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamientoDeConsulta {
    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosCancelamientoConsulta datos) {
        Consulta consulta = repository.getReferenceById(datos.idConsulta());
        LocalDateTime ahora = LocalDateTime.now();
        long diferenciaEnHoras = Duration.between(ahora, consulta.getFecha()).toHours();

        if (diferenciaEnHoras < 24) {
            throw new ValidationException("Consulta solamente puede ser cancelada con antecedencia mínima de 24h!");
        }
    }
}
