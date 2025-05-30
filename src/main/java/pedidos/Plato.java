package pedidos;

public class Plato extends Producto{


    public Plato(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public double getPrecio() {
        return precio;
    }
}
