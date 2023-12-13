package S1.Testing.Nivel_1.Ejercicio1;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class S1_T4_Ejercicio_1_TEST {
    //Debido a que la variable "months" no será modificada en estos test propuestos, la creo una sola vez y la uso en
    // todos los tests:
    private OrderedMonths months = new OrderedMonths();

    @BeforeAll
    public static void setUpInstance()
    {
//            Funcionaba tal cual esta. Al agegar el "BeforeAll", tira un error.
//            Encontré que para solucionar esto, debo hacer STATIC tanto el metodo "setUpInstance" como las instancias. ¿POR que?


    }
    @Test
    // Test "La lista tiene 12 posiciones"
    public void testPosiciones()
    {
        System.out.println("::: Test - 'La lista tiene 12 posiciones' :::");
        int esperado = 12;
        int result = this.months.sortedMonths.size();
        assertEquals(esperado,result);
    }
    @Test
    public void testCollectionNotNull()
    {
        // ¿ Por que no se muestra el mensaje ?
        System.out.println("::: Test - '¿Hay Meses Cargados?' :::");
        assertNotNull(this.months, "Hay meses cargados");
    }

    @Test
    // Check del mes de Agosto (mes 8 ) en la posicion 7 del Array.
    public void testAgosto()
    {
        System.out.println("::: Test - 'El mes de Agosto es el numero 7 de la lista' :::");
        int indexWanted = 7;
        assertEquals(this.months.sortedMonths.get(indexWanted),"Agosto");

    }
}


