package med.voll.api.models.direccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record DatosDireccion(
        @NotBlank
        String calle,
        @NotBlank
        String distrito,
        @NotBlank
        String ciudad,
        @Positive(message = "Numero debe de ser positivo")
        Integer numero,
        @NotBlank
        String complemento) {

}
