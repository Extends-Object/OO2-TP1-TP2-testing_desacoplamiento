import concurso.utilidades.exceptions.DatabaseConnectionException;
import pedidos.persistencia.Repositorio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPedidosFake implements Repositorio {
    private final List<String> listaPedidos = new ArrayList<>();

    @Override
    public void guardarPedido(LocalDate fecha, double monto) throws DatabaseConnectionException {
        this.listaPedidos.add("Fecha: " + fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", Monto: " + String.valueOf(monto));
    }

    public List<String> getListaPedidos() {
        return listaPedidos;
    }
}
