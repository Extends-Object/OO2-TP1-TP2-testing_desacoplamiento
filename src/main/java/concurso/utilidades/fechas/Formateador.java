package concurso.utilidades.fechas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Formateador {

    public String formatearFecha(LocalDate fecha){
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
