package med.voll.api.dto.medico;

import med.voll.api.models.direccion.DatosDireccion;

public record DatosRespuestaMedico(
    Long id,
    String nombre,
    String email,
    String telefono,
    String documento,
    DatosDireccion direccion
) {
}
