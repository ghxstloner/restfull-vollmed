package med.voll.api.models.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.dto.consultas.DatosAgendarConsulta;
import med.voll.api.models.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivo implements ValidadorDeConsultas{
    @Autowired
    private PacienteRepository repository;

    public void validar(DatosAgendarConsulta datos){
        if(datos.idPaciente()==null){
            return;
        }

        Boolean pacienteActivo = repository.findActivoById(datos.idPaciente());
        if(!pacienteActivo){
            throw new ValidationException("No se permiten agendas con pacientes inactivos");
        }

    }


}
