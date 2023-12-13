package S1.Generics.Nivel_3.Classes;
import S1.Generics.Nivel_3.Interfaces.Phone;

public class Smartphone implements Phone {

    public void call()
    {
        System.out.println("- This is the 'Smartphone´s CALL' Method");
    }

    public void takePicture()
    {
        System.out.println("- This is the 'Smartphone´s 'TakePicture' Method");
    }
}
