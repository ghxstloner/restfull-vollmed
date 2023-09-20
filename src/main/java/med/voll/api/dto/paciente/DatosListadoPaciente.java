package med.voll.api.dto.paciente;

import med.voll.api.models.paciente.Paciente;

public record DatosListadoPaciente(Long id, String nombre, String email, String documento, String telefono) {
    public DatosListadoPaciente(Paciente paciente) {
        this(paciente.getId(),paciente.getNombre(), paciente.getEmail(),paciente.getDocumento(),paciente.getTelefono());
    }

}
