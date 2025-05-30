import concurso.utilidades.fechas.Reloj;

import java.time.LocalDate;

public class RelojFake implements Reloj {

    private LocalDate fecha;

    public void setFechaActual(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public LocalDate obtenerFechaActual() {
        return fecha;
    }
}
