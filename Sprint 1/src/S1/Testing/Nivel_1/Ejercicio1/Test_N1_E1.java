package S1.Testing.Nivel_1.Ejercicio1;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test_N1_E1 {

    static OrderedMonths months; // = new OrderedMonths();
    @BeforeAll
    public static void setUpInstance()
//            Funcionaba tal cual esta. AL agegar el "BeforeAll", tira un error.
//            Encontré que para solucionar esto, debo hacer STATIC tanto el metodo "setUpInstance" como las instancias. ¿POR que?
    {
        String mes1 = "enero";String mes2 = "febrero";String mes3 = "marzo";String mes4 = "abril";String mes5 = "mayo";
        String mes6 = "junio";String mes7 = "julio";String mes8 = "agosto";String mes9 = "septiembre";
        String mes10 = "octubre";String mes11 = "noviembre";String mes12 = "diciembre";
        months = new OrderedMonths();

        months.insertMonth(mes10,10);
        months.insertMonth(mes1,1);
        months.insertMonth(mes3,3);
        months.insertMonth(mes6,6);
        months.insertMonth(mes8,8);
        months.insertMonth(mes11,11);
        months.insertMonth(mes2,2);
        months.insertMonth(mes4,4);
        months.insertMonth(mes7,7);
        months.insertMonth(mes9,9);
        months.insertMonth(mes5,5);
        months.insertMonth(mes12,12);
    }


    @Test
    // Test "La lista tiene 12 posiciones"
    public void testPosiciones()
    {
        System.out.println("::: Test - 'La lista tiene 12 posiciones' :::");
        int esperado = 12;
        assertEquals(esperado,months.sortedMonths.size());
    }


    @Test
    public void testCollectionNotNull()
    {
        // ¿ Por que no se muestra el mensaje ?
        assertNotNull(months, "Hay meses cargados");
    }




   /*
    @Test
    public void testEighthPosition()
    {

        String mes1 = "enero";String mes2 = "febrero";String mes3 = "marzo";String mes4 = "abril";String mes5 = "mayo";
        String mes6 = "junio";String mes7 = "julio";String mes8 = "agosto";String mes9 = "septiembre";
        String mes10 = "octubre";String mes11 = "noviembre";String mes12 = "diciembre";
        OrderedMonths months = new OrderedMonths();
        months.insertMonth(mes10,10);
        months.insertMonth(mes1,1);
        months.insertMonth(mes3,3);
        months.insertMonth(mes6,6);
        months.insertMonth(mes8,8);
        months.insertMonth(mes11,11);
        months.insertMonth(mes2,2);
        months.insertMonth(mes4,4);
        months.insertMonth(mes7,7);
        months.insertMonth(mes9,9);
        months.insertMonth(mes5,5);
        months.insertMonth(mes12,12);


        String esperado = "agosto";
        assertEquals(esperado,months.getMonth(9));
    }
   */

}


