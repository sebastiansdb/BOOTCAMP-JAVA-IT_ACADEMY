package S1.Excepciones.Nivel_1.Ejercicio1;

public class App1 {

    public static void main(String[] args)  {
        Producto p1 = new Producto("Fideos", 3);
        Producto p2 = new Producto("Leche", 2.5);
        Producto p3 = new Producto("Cacao", 3.78);

        Venta v1 = new Venta();
        Venta v2 = new Venta();
        v1.setProductoVenta(p1);
        v1.setProductoVenta(p2);
        v1.setProductoVenta(p3);
        // Runtime sin excepciones potenciales
        try {
            v1.calcularTotal();
            System.out.println("El total de la venta es: " + v1.getValorVenta() );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Excepcion Personalizada
        try {
            // Venta Vacia
            v2.calcularTotal();
            System.out.println(v2.getValorVenta());
        } catch (ExcepcionVentaVacia e) {
            System.out.println(e.getMessage());
        }
        // IndexOutOfBound Exception
        try {
            Producto Producto10 = v1.getProductosVenta().get(10);
        }catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
