package S1.Excepciones.Nivel_1.Ejercicio1;
import java.util.ArrayList;
public class Venta {

    private ArrayList<Producto> productosVenta;
    private double valorVenta;

    public Venta(){
        valorVenta = 0;
        productosVenta = new ArrayList<Producto>();
    }

    // Getters
    public double getValorVenta(){
        return valorVenta;
    }

    public ArrayList<Producto> getProductosVenta() {
        return productosVenta;
    }

    // Setters
    public void setProductoVenta (Producto producto){
        productosVenta.add(producto);
    }

    // Metodos de Venta
    public void calcularTotal() throws ExcepcionVentaVacia {
        double valorVenta = 0;
        int contProductos = 0;

        if (productosVenta.size() == 0 ){
            throw new ExcepcionVentaVacia("Per fer una venda primer has d’afegir productes");
        } else {
        do {
                valorVenta += productosVenta.get(contProductos).getPrecio();
                contProductos++;
            } while (contProductos < productosVenta.size());
            this.valorVenta = valorVenta;

        }
    }
}
