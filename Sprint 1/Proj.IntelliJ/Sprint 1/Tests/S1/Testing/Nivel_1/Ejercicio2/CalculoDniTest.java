package S1.Testing.Nivel_1.Ejercicio2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CalculoDniTest
{
    /**
     *                                                  Tabla DNI´s
     * RESTO 	0 	1 	2 	3 	4 	5 	6 	7 	8 	9 	10 	11  12 	13 	14 	15 	16 	17 	18 	19 	20 	21 	22
     * LETRA 	T 	R 	W 	A 	G 	M 	Y 	F 	P 	D 	X 	B   N 	J 	Z 	S 	Q 	V 	H 	L 	C 	K 	E
     */


    /**
     * TEST PARAMETRIZADO

     * Se usa cuando debemos ejecutar un test N veces, es decir, el mismo test con una cantidad N de parámetros
     * distintos.
     */

    @ParameterizedTest
    @MethodSource (value = "getData") // Nombre del metodo "proveedor" que contiene los parámetros para ejecutar el test
    @DisplayName(value = "::: Test - 'Dado un numero específico de DNI, corroborar su letra' :::")
    public void testLetrasyNumerosDni(char letraEsperada, int dniTest)
    {
        CalculoDni calculadorDni = new CalculoDni();
        assertEquals(letraEsperada,calculadorDni.obtenerLetraDni(dniTest));
    }

    /**
     * JUnit ya sabe que tiene que tomar el par de valores de cada "Object[]" contenidos en la "List<Object[]> ret" y
     * pasarlos, en el mismo orden en que se encuentran almacenados, como argumentos de la funcion
     * "testLetrasyNumerosDni". Esto se hará de manera iterativa con todos los elementos de la lista nombrada.
     */
    public static Iterable<Object[]> getData()
    {
        List<Object[]> ret = new ArrayList<>();
        ret.add(new Object[] {'N',2345023} );
        ret.add(new Object[]{'A',12345023});
        ret.add(new Object[]{'V',22345023});
        ret.add(new Object[]{'P',32345023});
        ret.add(new Object[]{'E',42345023});
        ret.add(new Object[]{'J',52345023});
        ret.add(new Object[]{'G',62345023});
        ret.add(new Object[]{'H',72345023});
        ret.add(new Object[]{'D',82345023});
        ret.add(new Object[]{'C',80345023});
        return ret;
    }
}

