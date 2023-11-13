/*
    Tots els mètodes reben un String amb el missatge que es vol mostrar a l’usuari/ària, per exemple: “Introdueix la teva edat”,
    i retornen la dada oportuna introduïda per l’usuari/ària en cada mètode, per exemple: un byte amb l’edat de l’usuari/ària.

Mètodes a implantar capturant l’excepció de la classe InputMismatchException:

    public static byte llegirByte(String missatge);

    public static int llegirInt(String missatge);

    public static float llegirFloat(String missatge);

    public static double llegirDouble(String missatge);

Mètodes a implantar capturant l’excepció de la classe Exception:

    public static char llegirChar(String missatge);

    public static String llegirString(String missatge);

    public static boolean llegirSiNo(String missatge), si l’usuari/ària introdueix “s”, retorna “true”, si l’usuari introdueix “n”, retorna “false”.

     */
package S1.Excepciones.Nivel_2.Ejercicio1;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Entrada {

    // Metodos que capturan "InputMismatchException"
    public static byte getByte(String message){
        Scanner sc = new Scanner(System.in);
        byte ret = 0;
        boolean exceptionFlag = false;
        System.out.println(message);
        do {
            try {
                ret = sc.nextByte();
                exceptionFlag = true;
            } catch (InputMismatchException e) {
                System.out.println("Valor fuera de rango. Ingresa un byte...");
                /* Se arma un bucle infinito si no pongo esta linea: */
                sc.nextLine();
            }
        } while(!exceptionFlag);
        return ret;
    }
    public static int getInt(String message) {
        Scanner sc = new Scanner(System.in);
        int ret = 0;
        boolean exceptionFlag = false;
        System.out.println(message);
        do {
            try {
                ret = sc.nextInt();
                exceptionFlag = true;
            } catch (InputMismatchException e) {
                System.out.println("Valor fuera de rango, debes ingresar un int...\n");
                sc.nextLine();
                System.out.println(message);
            }
        }  while (!exceptionFlag);
        return ret;
    }
    public static float getFloat(String message) {
        Scanner sc = new Scanner(System.in);
        float ret = 0;
        boolean exceptionFlag = true;
        System.out.println(message);
        do {
            try {
                ret = sc.nextFloat();
                exceptionFlag = false;
            } catch(InputMismatchException e){
                System.out.println("El numero ingresado no es un float. Ingresa un Float...\n" );
                sc.nextLine();
            }
        }while(exceptionFlag);
        return ret;

    }

    public static Double getDouble(String message){
        Scanner sc = new Scanner(System.in);
        Double ret = 0.0;
        boolean exceptionFlag = true;
        System.out.println(message);
        do{
            try {
                ret = sc.nextDouble();
                exceptionFlag = false;
            }catch(InputMismatchException e){
                System.out.println("El numero ingresado no es un Double. Ingresa un Double...\n" );
                sc.nextLine();
            }
        } while(exceptionFlag);
        return ret;
    }

    // Metodos que lanzan "Exception"
    public static String getString(String message) {
        Scanner sc = new Scanner(System.in);
        String ret = "0";
        boolean exceptionFlag = false;
        System.out.println(message);
        do {
            try {
                ret = sc.nextLine();
                if (ret.length() > 1) {
                    exceptionFlag = true;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Valor no válido, debes ingresar un String...\n");
            }
        }  while (!exceptionFlag);
        return ret;
    }

    public static char getChar(String message) {
        Scanner sc = new Scanner(System.in);
        char ret = '0';
        String input;
        boolean exceptionFlag = false;

        System.out.println(message);
        do {
            try {
                input = sc.nextLine();
                if (input.length() == 1) {
                    ret = input.charAt(0);
                    exceptionFlag = true;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Valor no válido, debes ingresar un Char...\n");
            }
        } while (!exceptionFlag);
        return ret;
    }

    public static boolean readYesNo(String message) {
        Scanner sc = new Scanner(System.in);
        boolean ret = false;
        char input;
        boolean exceptionFlag = false;
        System.out.println(message);
        do {
            try {
                input = sc.nextLine().toLowerCase().charAt(0);
                if (input == 'y'){
                    ret = true;
                    exceptionFlag = true;
                } else if (input == 'n'){
                    ret = false;
                    exceptionFlag = true;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Valor no válido, debes ingresar Y o N...\n");
            }
        }  while (!exceptionFlag);
        return ret;
    }

}
