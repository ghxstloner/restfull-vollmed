package med.voll.api.dto.consultas;

import jakarta.validation.constraints.NotNull;
import med.voll.api.models.consulta.cancelamiento.MotivoCancelamiento;

public record DatosCancelamientoConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamiento motivo) {
}