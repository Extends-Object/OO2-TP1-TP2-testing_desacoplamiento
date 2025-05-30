import concurso.Concurso;
import concurso.Participante;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ConcursoTest {

    private RepositorioConcursoFake repositorio;
    private NotificadorFake notificador;
    private RelojFake reloj;

    private Concurso concurso;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    @BeforeEach
    public void setUp() {
        fechaInicio = LocalDate.of(2025, 5, 1);
        fechaFin = LocalDate.of(2025, 5, 31);
        reloj = new RelojFake();
        repositorio = new RepositorioConcursoFake();
        notificador = new NotificadorFake();
        concurso = new Concurso("Concurso Test", fechaInicio, fechaFin, repositorio, notificador, reloj);
    }

    @Test
    public void inscribirPrimerDiaTest() throws Exception {
        reloj.setFechaActual(fechaInicio);
        Participante participante = new Participante("Rosa", "rosa@mail.com");

        String resultado = concurso.inscribirParticipante(participante);

        assertEquals("Se realizó la inscripción.", resultado, "No se pudo realizar la inscripción.");
        assertEquals(10, participante.getPuntosAcumulados(), "Los puntos se han asignado correctamente.");
        assertTrue(repositorio.inscriptos.contains(participante), "El participante no se encuentra en la lista de inscriptos.");
        assertTrue(notificador.notificados.contains("rosa@mail.com"), "No se pudo notificar.");
    }

    @Test
    public void inscribirFueraDeFechaTest() throws Exception {
        reloj.setFechaActual(fechaFin.plusDays(1));
        Participante participante = new Participante("Dominga", "dominga@mail.com");

        String resultado = concurso.inscribirParticipante(participante);

        assertEquals("No se puede inscribir: la inscripción está cerrada.", resultado);
        assertFalse(repositorio.inscriptos.contains(participante), "El resultado no es el esperado.");
        assertFalse(notificador.notificados.contains("dominga@mail.com"), "El resultado no es el esperado.");
    }

    @Test
    public void inscribirSinPuntosTest() throws Exception{
        reloj.setFechaActual(fechaInicio.plusDays(5));
        Participante participante = new Participante("Isabel", "isabel@mail.com");

        String resultado = concurso.inscribirParticipante(participante);

        assertEquals("Se realizó la inscripción.", resultado, "No se pudo realizar la inscripción.");
        assertEquals(0, participante.getPuntosAcumulados(), "Los puntos se han asignado correctamente.");
        assertTrue(repositorio.inscriptos.contains(participante), "El participante no se encuentra en la lista de inscriptos.");
        assertTrue(notificador.notificados.contains("isabel@mail.com"), "No se pudo notificar.");
    }

}