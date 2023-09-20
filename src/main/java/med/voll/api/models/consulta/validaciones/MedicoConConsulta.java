package med.voll.api.models.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.dto.consultas.DatosAgendarConsulta;
import med.voll.api.models.consulta.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoConConsulta implements ValidadorDeConsultas{
    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosAgendarConsulta datos){
        if(datos.idMedico() == null){
            return;
        }
        Boolean medicoConConsulta = repository.existsByMedicoIdAndFecha(datos.idMedico(),datos.fecha());
        if(medicoConConsulta){
            throw new ValidationException("El medico ya tiene una consulta");
        }

    }
}
