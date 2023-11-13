package S1.Testing.Nivel_1.Ejercicio2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_N1_E2 {

    @Test
    public void test1()
    {
        App2 calculador = new App2();
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
        assertEquals(esperado0,calculador.obtenerLetraDni(numero0));    // ¿ A qué se refieren los WARNINGS que arroja?
        assertEquals(esperado1,calculador.obtenerLetraDni(numero1));
        assertEquals(esperado2,calculador.obtenerLetraDni(numero2));
        assertEquals(esperado3,calculador.obtenerLetraDni(numero3));
        assertEquals(esperado4,calculador.obtenerLetraDni(numero4));
        assertEquals(esperado5,calculador.obtenerLetraDni(numero5));
        assertEquals(esperado6,calculador.obtenerLetraDni(numero6));
        assertEquals(esperado7,calculador.obtenerLetraDni(numero7));
        assertEquals(esperado8,calculador.obtenerLetraDni(numero8));
        assertEquals(esperado9,calculador.obtenerLetraDni(numero9));
    }
}
