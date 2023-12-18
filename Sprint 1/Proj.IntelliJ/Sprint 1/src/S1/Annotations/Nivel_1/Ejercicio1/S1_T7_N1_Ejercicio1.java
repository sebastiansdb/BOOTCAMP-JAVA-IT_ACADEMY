package S1.Annotations.Nivel_1.Ejercicio1;

public class S1_T7_N1_Ejercicio1 {
    @SuppressWarnings("deprecation")
    public static void main(String[] args){
        TrabajadorOnline tO1 = new TrabajadorOnline("Juan", "Perez", 100);
        TrabajadorPresencial tP1 = new TrabajadorPresencial("Juan", "Gonzales",120);

        // Ejercicio 1
        System.out.println("El sueldo del Trabajador Online " + tO1.getName() +" es: " +  tO1.calcularSueldo(40));
        System.out.println("El sueldo del Trabajador Presencial: " + tP1.getName() +" es: " + tP1.calcularSueldo(30));

        // Ejercicio 2
        // Este método está marcado como obsoleto
        tO1.metodoObsoletoOnline();
        // Este método está marcado como obsoleto
        tP1.metodoObsoletoPresencial();
    }
}
