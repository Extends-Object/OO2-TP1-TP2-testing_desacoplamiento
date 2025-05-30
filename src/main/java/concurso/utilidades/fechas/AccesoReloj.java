package concurso.utilidades.fechas;

import java.time.LocalDate;

public class AccesoReloj implements Reloj {
    @Override
    public LocalDate obtenerFechaActual() {
        return LocalDate.of(2025, 03, 15);
    }

}
