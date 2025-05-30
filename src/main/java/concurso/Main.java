package concurso;

import concurso.persistencia.Repositorio;
import concurso.persistencia.RepositorioArchivo;
import concurso.persistencia.RepositorioBD;
import concurso.utilidades.exceptions.DatabaseConnectionException;
import concurso.utilidades.fechas.AccesoReloj;
import concurso.utilidades.fechas.Formateador;
import concurso.utilidades.fechas.Reloj;
import concurso.utilidades.notificadores.Notificador;
import concurso.utilidades.notificadores.NotificadorEmail;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

//------------------------------------------------ SET UP --------------------------------------------------------------
        Participante p1 = new Participante("Pepito Perez", "pepito@mail.com");
        Participante p2 = new Participante("Maria Suarez", "maria@mail.com");
        Participante p3 = new Participante("Juana Rodriguez", "juana@mail.com");
        Participante p4 = new Participante("Dominga Gomez", "dominga@mail.com");

        LocalDate fechaInicio =  LocalDate.of(2025, 03, 15);
        LocalDate fechaFin = LocalDate.of(2025, 03, 28);

//----------------------------------------------- REGISTRO EN ARCHIVO --------------------------------------------------

        String rutaArchivo = "C:\\Users\\retur\\OneDrive\\Escritorio\\UNRN\\TERCER AÑO\\PRIMER CUATRIMESTRE\\OBJETOS 2\\ArchivosJava\\Inscriptos.txt";
        Reloj reloj = new AccesoReloj();
        Formateador formateador = new Formateador();

        Repositorio repositorioArchivo = new RepositorioArchivo(rutaArchivo, formateador);
        Notificador notificador = new NotificadorEmail();

        Concurso concurso1 = new Concurso("Concurso 1", fechaInicio, fechaFin, repositorioArchivo, notificador, reloj);


        System.out.println("Inscribir participantes (archivo): ");
        try {
            System.out.println(concurso1.inscribirParticipante(p1));
            System.out.println(concurso1.inscribirParticipante(p2));
        } catch (DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }

//------------------------------------------- REGISTRO EN BASE DE DATOS ------------------------------------------------

        Repositorio repositorioBD = new RepositorioBD();

        Concurso concurso2 = new Concurso("Concurso 2", fechaInicio, fechaFin, repositorioBD, notificador, reloj);

        System.out.println("Inscribir participantes (bd): ");
        try {
            System.out.println(concurso2.inscribirParticipante(p3));
            System.out.println(concurso2.inscribirParticipante(p4));
        } catch (DatabaseConnectionException e) {
            System.out.println(e.getMessage());
        }
    }
}
