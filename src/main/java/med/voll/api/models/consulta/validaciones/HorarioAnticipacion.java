package med.voll.api.models.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.dto.consultas.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioAnticipacion implements ValidadorDeConsultas{
    public void validar(DatosAgendarConsulta datos){
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime horaConsulta = datos.fecha();

        boolean diferencia30Min = Duration.between(ahora,horaConsulta).toMinutes()<30;
        if(diferencia30Min){
            throw new ValidationException("Las consultas deben programarse con al menos 30 minutos de anticipación");
        }

    }


}
