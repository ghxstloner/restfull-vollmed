package med.voll.api.models.consulta.cancelamiento;

import med.voll.api.dto.consultas.DatosCancelamientoConsulta;

public interface ValidadorCancelamientoDeConsulta {
    void validar(DatosCancelamientoConsulta datos);
}
