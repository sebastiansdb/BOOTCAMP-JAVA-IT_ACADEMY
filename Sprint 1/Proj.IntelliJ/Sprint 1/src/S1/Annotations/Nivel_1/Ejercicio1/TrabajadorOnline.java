package S1.Annotations.Nivel_1.Ejercicio1;

public class TrabajadorOnline extends Trabajador{

    // ¿¿ Es buena práctica definir el valor de una CONSTANTE (como atributo de clase) cuando la declaro o es mejor en
    // el constructor ??
    // La declaro aquí y la inicializo en el constructor.
    private final int tarifaPlanaInternet;

    public TrabajadorOnline(String nombre, String apellido, float precioPorHora)
    {
        super(nombre, apellido, precioPorHora);
        tarifaPlanaInternet = 10;
    }
    public String getName()
    {
        return super.getName();
    }
    @Override
    public float calcularSueldo(float horasTrabajadasAlMes) {
        return super.calcularSueldo(horasTrabajadasAlMes) + tarifaPlanaInternet;
    }
    // Metodo Obsoleto

    // Este método está marcado como obsoleto y se sugiere no utilizarlo.
    @Deprecated
    public void metodoObsoletoOnline()
    {
        System.out.println("Metodo Obsoleto de clase TrabajadorOnline");
    }
}
