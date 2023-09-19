package med.voll.api.models.consulta;

import med.voll.api.dto.consultas.DatosAgendarConsulta;
import med.voll.api.infra.exceptions.ValidacionDeIntegridad;
import med.voll.api.models.medico.Medico;
import med.voll.api.models.medico.MedicoRepository;
import med.voll.api.models.paciente.Paciente;
import med.voll.api.models.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AgendaConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public void agendar(DatosAgendarConsulta datos){

        if(pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("No se encontró el ID del paciente");
        }
        
        if(datos.id()!= null && medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("No se encontró el ID del medico");
        }

        Paciente paciente = pacienteRepository.findById(datos.idPaciente()).get();
        Medico medico = seleccionarMedico(datos);
        Consulta consulta = new Consulta(null,medico,paciente,datos.fecha());
        consultaRepository.save(consulta);
    }

    private Medico seleccionarMedico(DatosAgendarConsulta datos) {
        if(datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad()==null){
            throw new ValidacionDeIntegridad(("Debe seleccionar una especialidad"));
        }
        return medicoRepository.selecionarMedicoConEspecialidadMasFecha(datos.especialidad(),datos.fecha());
    }
}
