package med.voll.api.dto.paciente;

import med.voll.api.models.direccion.DatosDireccion;

public record DatosRespuestaPaciente(
        Long id,
        String nombre,
        String email,
        String documento,
        String telefono,
        DatosDireccion direccion
) {
}