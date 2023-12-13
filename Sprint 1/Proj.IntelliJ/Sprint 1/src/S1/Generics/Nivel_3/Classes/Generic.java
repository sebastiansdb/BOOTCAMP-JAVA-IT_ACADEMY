package S1.Generics.Nivel_3.Classes;

import S1.Generics.Nivel_3.Interfaces.Phone;

public class Generic {
    /**
     * En este caso, "<T extends Phone>" establece un límite superior para T. Esto significa que T debe ser una subclase
     * de (o implementar) la interfaz Phone.  Esto permite una mayor flexibilidad al escribir código y garantiza que el
     * tipo utilizado sea compatible con la interfaz o clase esperada.
     * <T extends Phone> se coloca al principio de la declaración de un método para indicar que el método utiliza
     * generics y para establecer un límite superior en el tipo de datos que se puede usar como argumento para ese método.
     *  Esta restricción es conocida como un límite superior y asegura que cualquier tipo con el que se llame al método
     *  sea compatible con la clase Phone (o sus subclases).
     *  Colocar la declaración de generics al principio proporciona información sobre las restricciones y expectativas
     *  del método antes de entrar en los detalles específicos del método en sí.
    */
    public static <T extends Phone> void callCallMethod(T obj)
    {
            obj.call();
    }
    public static <T extends Smartphone> void callSmartPhoneClassMethods(T obj)
    {
        obj.call();
        obj.takePicture();
    }
}
