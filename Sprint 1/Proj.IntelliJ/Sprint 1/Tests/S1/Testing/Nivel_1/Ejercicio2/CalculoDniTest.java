package S1.Testing.Nivel_1.Ejercicio2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class CalculoDniTest {
    @Test
    public void testLetrasyNumerosDni()
    {
        /**
         *                                                  Tabla DNI´s
         * RESTO 	0 	1 	2 	3 	4 	5 	6 	7 	8 	9 	10 	11  12 	13 	14 	15 	16 	17 	18 	19 	20 	21 	22
         * LETRA 	T 	R 	W 	A 	G 	M 	Y 	F 	P 	D 	X 	B   N 	J 	Z 	S 	Q 	V 	H 	L 	C 	K 	E
         */
        System.out.println("::: Test - 'Dado un numero específico de DNI, corroborar su letra' :::");

        CalculoDni calculadorDni = new CalculoDni();
        char[] letrasEsperadas = new char[]{'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q',
                    'V','H', 'L','C','K','E'};
        int numero0 = 2345023;  // Letra 'N'
        int numero1 = 12345023; // Letra 'A'
        int numero2 = 22345023; // Letra 'V'
        int numero3 = 32345023; // Letra 'P'
        int numero4 = 42345023; // Letra 'E'
        int numero5 = 52345023; // Letra 'J'
        int numero6 = 62345023; // Letra 'G'
        int numero7 = 72345023; // Letra 'H'
        int numero8 = 82345023; // Letra 'D'
        int numero9 = 80345023; // Letra 'C'
        assertEquals(letrasEsperadas[12],calculadorDni.obtenerLetraDni(numero0));
        assertEquals(letrasEsperadas[3],calculadorDni.obtenerLetraDni(numero1));
        assertEquals(letrasEsperadas[17],calculadorDni.obtenerLetraDni(numero2));
        assertEquals(letrasEsperadas[8],calculadorDni.obtenerLetraDni(numero3));
        assertEquals(letrasEsperadas[22],calculadorDni.obtenerLetraDni(numero4));
        assertEquals(letrasEsperadas[13],calculadorDni.obtenerLetraDni(numero5));
        assertEquals(letrasEsperadas[4],calculadorDni.obtenerLetraDni(numero6));
        assertEquals(letrasEsperadas[18],calculadorDni.obtenerLetraDni(numero7));
        assertEquals(letrasEsperadas[9],calculadorDni.obtenerLetraDni(numero8));
        assertEquals(letrasEsperadas[20],calculadorDni.obtenerLetraDni(numero9));
    }
}

