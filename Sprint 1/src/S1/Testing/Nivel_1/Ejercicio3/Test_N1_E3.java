package S1.Testing.Nivel_1.Ejercicio3;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Test_N1_E3 {

    @Test
    public void testRigthFuntionality() throws IOException {

//        assertDoesNotThrow();
        ArrayList<String> al = new ArrayList<>();
        al.add("Oso");
        al.add("Perro");
        al.add("Gato");
        App3 testObject = new App3();
        //assertThrows(IOException.class,testObject.readArrayElement(al,4));
    }
}

