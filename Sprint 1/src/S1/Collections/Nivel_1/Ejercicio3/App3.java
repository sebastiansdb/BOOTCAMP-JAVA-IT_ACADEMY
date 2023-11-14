package S1.Collections.Nivel_1.Ejercicio3;

import java.util.HashMap;
import java.util.Scanner;

public class App3 {

    static  Scanner sc = new Scanner(System.in);
    public static void main (String[] args)
    {
        HashMap<String,String> countriesAndCapitals;
        String userName;
        byte userPunctuation;
        String fileRoute = "/Users/sebastian/Coding/Java/IT_Academy/Java/Java con Spring Framework/Sprint 1/src/S1" +
                "/Collections/Nivel_1/Ejercicio3/countries.txt";
        String fileOutRoute = "/Users/sebastian/Coding/Java/IT_Academy/Java/Java con Spring Framework/Sprint 1/" +
                "src/S1/Collections/Nivel_1/Ejercicio3/Puntuaciones.txt";
        // Leo archivo txt
        countriesAndCapitals = ReadFile.txtFileToHashMap(fileRoute);
        userName = askName();
        // Pregunto al usuario
        userPunctuation = survey (countriesAndCapitals);
        WriteFile.writeFile(userName,userPunctuation,fileOutRoute);
        System.out.println("Tu puntuación final es: " + userPunctuation);
    }

    public static String askName ()
    {
        String ret;
        System.out.println("Introduce nombre de Usuario");
        ret = sc.nextLine();
        return ret;
    }

    // Crea numero aleatorio entre "min" y "max" = 51 (Cantidad de paises que hay en el archivo .txt)
    static int randomBetween (int min, int max)
    {
        return (int) (Math.random() * (max+1-min)) + min;
    }

    public static byte survey(HashMap<String,String> countriesAndCapitals){
        Scanner sc = new Scanner(System.in);
        byte userPunctuation = 0;
        String userAnswer;
        int answerTimes = 10;
        for (int i = 0;  i < answerTimes; i++) {
            String countryAsked = (String) countriesAndCapitals.keySet().toArray()[randomBetween(0,countriesAndCapitals.size())];
            System.out.println("Cuál es la capital de '" + countryAsked + "' ?");
            userAnswer = sc.nextLine();
            if (countriesAndCapitals.get(countryAsked).equalsIgnoreCase(userAnswer)) {
                userPunctuation++;
            }
        }
        return userPunctuation;
    }
}
