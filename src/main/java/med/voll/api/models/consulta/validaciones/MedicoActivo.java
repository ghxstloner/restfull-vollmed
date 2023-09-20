package med.voll.api.models.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.dto.consultas.DatosAgendarConsulta;
import med.voll.api.models.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicoActivo implements ValidadorDeConsultas{
    @Autowired
    private MedicoRepository repository;

    public void validar(DatosAgendarConsulta datos){
        if(datos.idMedico()==null){
            return;
        }

        Boolean medicoActivo = repository.findActivoById(datos.idMedico());
        if(!medicoActivo){
            throw new ValidationException("No se permiten agendas con medicos inactivos");
        }

    }


}
