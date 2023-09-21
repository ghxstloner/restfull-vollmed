package med.voll.api.models.consulta;

import med.voll.api.dto.consultas.DatosAgendarConsulta;
import med.voll.api.dto.consultas.DatosCancelamientoConsulta;
import med.voll.api.dto.consultas.DatosDetalleConsulta;
import med.voll.api.infra.exceptions.ValidacionDeIntegridad;
import med.voll.api.models.consulta.cancelamiento.ValidadorCancelamientoDeConsulta;
import med.voll.api.models.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.models.medico.Medico;
import med.voll.api.models.medico.MedicoRepository;
import med.voll.api.models.paciente.Paciente;
import med.voll.api.models.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AgendaConsultaService {
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidadorDeConsultas> validadores;

    @Autowired
    private List<ValidadorCancelamientoDeConsulta> validadoresCancelamiento;

    public DatosDetalleConsulta agendar(DatosAgendarConsulta datos){

        if(!pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("No se encontró el ID del paciente");
        }
        
        if(datos.id()!= null && !medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("No se encontró el ID del medico");
        }

        validadores.forEach(v->v.validar(datos));

        Paciente paciente = pacienteRepository.findById(datos.idPaciente()).get();
        Medico medico = seleccionarMedico(datos);
        if(medico == null){
            throw new ValidacionDeIntegridad("No existen medicos disponibles para este horario");
        }

        Consulta consulta = new Consulta(medico,paciente,datos.fecha());
        consultaRepository.save(consulta);

        return new DatosDetalleConsulta(consulta);
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

    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionDeIntegridad("Id de la consulta informado no existe!");
        }

        validadoresCancelamiento.forEach(v -> v.validar(datos));

        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }

}
