package med.voll.api.models.consulta.validaciones;

import med.voll.api.dto.consultas.DatosAgendarConsulta;

public interface ValidadorDeConsultas {
     void validar(DatosAgendarConsulta datos);
}
