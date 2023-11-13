package S1.Testing.Nivel_1.Ejercicio2;

public class App2 {

    public static void main(String[] args)
    {

        // Usé esto para saber qué letra le corresponde a los numeros que voy a testear.
        char esperado0 = 'L';
        char esperado1 = 'C';
        char esperado2 = 'Q';
        char esperado3 = 'H';
        char esperado4 = 'V';
        char esperado5 = 'M';
        char esperado6 = 'D';
        char esperado7 = 'R';
        char esperado8 = 'I';
        char esperado9 = 'V';
        int numero0 = 2345023;
        int numero1 = 12345023;
        int numero2 = 22345023;
        int numero3 = 32345023;
        int numero4 = 42345023;
        int numero5 = 52345023;
        int numero6 = 62345023;
        int numero7 = 72345023;
        int numero8 = 82345023;
        int numero9 = 88345023;

        char cero = obtenerLetraDni(numero6);
        char uno = obtenerLetraDni(numero7);
        char dos = obtenerLetraDni(numero9);
        System.out.println(cero);
        System.out.println(uno);
        System.out.println(dos);

    }

    public static char obtenerLetraDni(int dni)
    {
        // Uso la conversión a ASCII
        int resto = dni%23 + 64;
        return (char)resto;
    }
}
