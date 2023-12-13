package S1_T4_N1_Ejercicio1;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import static org.hamcrest.core.Is.is;

class CheckLongitudStringTest {

    // Creo Matcher Customizado
    public static Matcher<String> LengthComparator(Matcher<Integer> matcher) {
        /**
         *  Aquí estás creando una instancia de "FeatureMatcher".
         *  El primer parámetro (String) especifica el tipo del objeto sobre el cual estás aplicando este matcher (en
         *  este caso, String).
         *  El segundo parámetro (Integer) especifica el tipo de la característica que estás comparando (Integer en este
         *  caso, que seria la longitud de dichco String).
         *  Matcher es el matcher interno que se utilizará para la comparación.
         *  "Las longitudes no coinciden" es la descripción que se utilizará si la comparación falla.
         *  "QUE ES ESTO" es otra parte de la descripción en caso de fallo.
         */

        return new FeatureMatcher<String, Integer>(matcher, "Check Longitud Palabra ",
                "Longitud incorrecta: ") {
            /**
             * La classe abstracta "FeatureMatcher" contiene el metodo abstracto "FeatureValueOf". Éste se implementa
             * para poder especificar la caracteristica/parámetro usada para realizar la comparación.
             * Dicho método "FeatureValueOf" se debe implementar en una subclase de "FeatureMatcher" o, para no crear
             * una subclase, se puede crear una clase anomnima dentro y sobreescribir el metodo en cuestion.
             */
            @Override
            protected Integer featureValueOf(String chain) {
                return chain.length();
            }
        };
    }
    @Test
    public void StringLength()
    {
        MatcherAssert.assertThat(CheckStringLengthWithHamcrest.getString(),LengthComparator(is(8)) );
    }
}