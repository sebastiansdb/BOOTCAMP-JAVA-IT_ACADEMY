package S1.Testing.Nivel_1.Ejercicio3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Exception_ArrayIndexOutOfBoundsTest {

    @Test
    public void testRigthFuntionality(){
        assertThrows(IndexOutOfBoundsException.class,()-> Exception_ArrayIndexOutOfBounds.readArrayElement(1));
    }
}