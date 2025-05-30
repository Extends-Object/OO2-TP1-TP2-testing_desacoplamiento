package concurso;

import concurso.persistencia.Repositorio;
import concurso.utilidades.exceptions.DatabaseConnectionException;
import concurso.utilidades.fechas.Reloj;
import concurso.utilidades.notificadores.Notificacion;
import concurso.utilidades.notificadores.Notificador;

import java.time.LocalDate;

public class Concurso {

    //ATRIBUTO DE CLASE
    private static int contador = 1;

    //ATRIBUTOS DE INSTANCIA
    private final int id;

    private String nombre;
    private LocalDate fechaInicioInscripcion;
    private LocalDate fechaFinInscripcion;
    private Repositorio repositorio;
    private Notificador notificador;
    private Reloj reloj;


    public Concurso(String nombre, LocalDate fechaInicio, LocalDate fechaFin, Repositorio repositorio, Notificador notificador, Reloj reloj) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas no pueden ser nulas");
        }

        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a fecha de finalización.");
        }

        this.id = this.contador++;
        this.nombre = nombre;
        this.fechaInicioInscripcion = fechaInicio;
        this.fechaFinInscripcion = fechaFin;

        this.repositorio = repositorio;
        this.notificador = notificador;
        this.reloj = reloj;
    }

    public String inscribirParticipante(Participante participante) throws DatabaseConnectionException {

        LocalDate fechaActual = reloj.obtenerFechaActual();

        //Aca solamente verifico si se puede inscribir o no si esta dentro de las fechas permitidas
        //Despues puedo verificar si se inscribió en el primer dia y sumarle los puntos si es el caso (con esto
        // me evito teber que instanciar clases inscripcion segun fechas)
        if (fechaActual.isBefore(fechaInicioInscripcion) || fechaActual.isAfter(fechaFinInscripcion)) {
            return "No se puede inscribir: la inscripción está cerrada.";
        }

        if (fechaActual.equals(fechaInicioInscripcion)) {
            participante.agregarPuntos(10);     //Suma los puntos si se cumple que es primer dia
        }

        this.repositorio.guardarInscripcion(fechaActual, participante, this);
        this.notificador.notificar(new Notificacion(participante.getEmail()));  //Se puede evitar?

        return "Se realizó la inscripción.";
    }

//-------------------------------------------------------- GETTERS -----------------------------------------------------
    public int getId() {
        return id;
    }
}