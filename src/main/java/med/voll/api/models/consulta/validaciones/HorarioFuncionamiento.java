package med.voll.api.models.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.dto.consultas.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioFuncionamiento implements ValidadorDeConsultas{
    public void validar(DatosAgendarConsulta datos){
        boolean domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        boolean apertura = datos.fecha().getHour()<7;
        boolean cierre = datos.fecha().getHour()>19;

        if(domingo || apertura || cierre){
            throw new ValidationException("El horario de atención de la clínica es de lunes a sábado de 07:00 a 19:00 horas");
        }


    }


}
