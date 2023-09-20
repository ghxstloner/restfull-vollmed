package med.voll.api.models.paciente;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.dto.paciente.DatosRegistrarPaciente;
import med.voll.api.models.direccion.Direccion;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String documento;
    private String telefono;
    private boolean activo;

    @Embedded
    private Direccion direccion;
    public Paciente(DatosRegistrarPaciente datosRegistrarPaciente){
        this.nombre = datosRegistrarPaciente.nombre();
        this.email = datosRegistrarPaciente.email();
        this.documento = datosRegistrarPaciente.documento();
        this.telefono = datosRegistrarPaciente.telefono();
        this.direccion = new Direccion(datosRegistrarPaciente.direccion());
    }

    public void desactivarPaciente() {
        this.activo = false;
    }
}
