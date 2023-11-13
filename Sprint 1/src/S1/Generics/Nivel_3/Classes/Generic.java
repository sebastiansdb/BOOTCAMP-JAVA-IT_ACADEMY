package S1.Generics.Nivel_3.Classes;

import S1.Generics.Nivel_3.Interfaces.Phone;

public class Generic {


//    En este caso, "<T extends Phone>" quiere decir que este metodo sólo podrá ser invocado con argumentos del tipo Phone y sus "subclases"??
//    El hecho de que la clase Smartphone implemente la interface Phone, la transforma en una especie de "clase hija" de Phone??
    public static <T extends Phone> void callInterfaceMethod(T obj)
    {
            obj.call();
    }
    public static <T extends Smartphone> void callPhoneClassMethods(T obj) // ¿ Qué pasa si saco el extends Smartphone??
    // Por qué no me acepta los metodos call y take picture?
    {
        obj.call();
        obj.takePicture();
    }
}
