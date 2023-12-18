package S1.Annotations.Nivel_1.Ejercicio1;

public class TrabajadorPresencial extends Trabajador {
    private static int benzina;

    // Definir las varibales estaticas en el bloque estatico. Aporta mayor claridad al codigo.
    static {
        benzina = 10;
    }

    public TrabajadorPresencial(String nombre, String apellido, float precioPorHora)
    {
        super(nombre, apellido, precioPorHora);
    }
    public String getName()
    {
        return super.getName();
    }
    @Override
    public float calcularSueldo(float horasTrabajadasAlMes) {
        return super.calcularSueldo(horasTrabajadasAlMes) + benzina;
    }
    // Metodo Obsoleto
    @Deprecated
    public void metodoObsoletoPresencial()
    {
        System.out.println("Metodo Obsoleto de clase TrabajadorPresencial");
    }
}
