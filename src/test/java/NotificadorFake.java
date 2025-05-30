import concurso.utilidades.notificadores.Notificacion;
import concurso.utilidades.notificadores.Notificador;

import java.util.ArrayList;
import java.util.List;

public class NotificadorFake implements Notificador {

    List<String> notificados = new ArrayList<>();

    @Override
    public void notificar(Notificacion notificacion) {

        this.notificados.add(notificacion.getDestinatario());
    }
}
