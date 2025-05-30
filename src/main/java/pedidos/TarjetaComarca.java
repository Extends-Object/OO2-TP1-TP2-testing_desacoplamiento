package pedidos;

public class TarjetaComarca implements Tarjeta {
    @Override
    public double calcularMontoFinal(double totalBebidas, double totalPlatos) {
        return (totalBebidas + totalPlatos) * 0.98;     //desc 2% sobre el total
    }
}