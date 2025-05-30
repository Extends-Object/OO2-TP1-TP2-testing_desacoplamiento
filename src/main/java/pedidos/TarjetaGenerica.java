package pedidos;

public class TarjetaGenerica implements Tarjeta {
    @Override
    public double calcularMontoFinal(double totalBebidas, double totalPlatos) {
        return totalBebidas + totalPlatos;      //sin desc
    }
}