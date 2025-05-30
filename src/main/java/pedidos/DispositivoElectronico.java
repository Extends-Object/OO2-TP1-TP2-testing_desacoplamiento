package pedidos;

import concurso.utilidades.exceptions.DatabaseConnectionException;
import pedidos.persistencia.Repositorio;
import java.time.LocalDate;

public class DispositivoElectronico {

    private Repositorio repositorio;

    public DispositivoElectronico(Repositorio repositorio) {
        this.repositorio = repositorio;
    }

    public float calcularTotal (Pedido pedido, Tarjeta tarjetaCredito, Propina propina, LocalDate fecha) throws DatabaseConnectionException {

        float subtotalBebidas = pedido.calcularSubtotalBebidas();
        float subtotalPlatos = pedido.calcularSubtotalPlatos();

        float total = (float) tarjetaCredito.calcularMontoFinal(subtotalBebidas, subtotalPlatos);
        float totalConPropina = total + propina.aplicar(total);             //propina puede ser 0.02, 0.03, 0.05

        this.repositorio.guardarPedido(fecha, totalConPropina);

        return totalConPropina;
    }

}