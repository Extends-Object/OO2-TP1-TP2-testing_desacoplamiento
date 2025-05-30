package concurso.utilidades.notificadores;

import concurso.Participante;

public class Notificacion {

    //String destinatario = "john.doe@your.domain";

    private String emisor;
    private String destinatario;
    private String asunto;
    private String cuerpo;

    public Notificacion(String destinatario) {
        this.emisor = "your.recipient@email.com";
        this.destinatario = destinatario;
        this.asunto = "Inscripción";
        this.cuerpo = "Usted se ha inscripto en el concurso.";
    }

    public String getEmisor() {
        return emisor;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }
}
