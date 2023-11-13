package S1.Excepciones.Nivel_2.Ejercicio1;

public class App2 {

    public static void main (String[] args) {
        byte b = Entrada.getByte("Ingresa byte");
        System.out.println(b);
        int i = Entrada.getInt("Ingresa un int");
        System.out.println(i);
        float f = Entrada.getFloat("Ingresa un float");
        System.out.println(f);
        Double d = Entrada.getDouble("Ingresa un double");
        System.out.println(d);
        String s = Entrada.getString("Ingresa un String");
        System.out.println(s);
        char c = Entrada.getChar("Ingresa un Char");
        System.out.println(c);
        boolean yN = Entrada.readYesNo("Ingresa un 'Y/N'");
        System.out.println("Has elegido " + yN);
    }
}
