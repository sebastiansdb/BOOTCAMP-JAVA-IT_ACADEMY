package S1.Collections.Nivel_1.Ejercicio3;

import java.util.HashMap;
import java.util.Scanner;

public class Survey {
    public static byte survey(HashMap<String,String> countriesAndCapitals)
    {
        Scanner sc = new Scanner(System.in);
        byte userPunctuation = 0;
        String userAnswer;
        int answerTimes = 3;
        for (int i = 0;  i < answerTimes; i++) {
            String countryAsked = (String) countriesAndCapitals.keySet().toArray()[randomBetween(0,countriesAndCapitals.size())];
            // Para no instanciar variables de Sistema fuera del main, uso la funcion getString de la clase App3
            userAnswer = App3.getString("Cuál es la capital de '" + countryAsked + "' ?");
            if (countriesAndCapitals.get(countryAsked).equalsIgnoreCase(userAnswer)) {
                userPunctuation++;
            }
        }
        return userPunctuation;
    }
    // Crea numero aleatorio entre "min" y "max" = 51 (Cantidad de paises que hay en el archivo .txt)
    static int randomBetween(int min, int max)
    {
        return (int) (Math.random() * (max+1-min)) + min;
    }
}
