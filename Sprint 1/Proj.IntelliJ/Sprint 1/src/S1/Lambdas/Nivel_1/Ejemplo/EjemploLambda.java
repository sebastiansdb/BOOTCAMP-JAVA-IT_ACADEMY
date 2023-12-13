package S1.Lambdas.Nivel_1.Ejemplo;

public class EjemploLambda {
        public static void main(String[] args) {
            // Utilizando una expresión lambda
            MiInterfazFuncionalEjemplo lambda = () -> System.out.println("Implementación mediante expresión lambda");
            lambda.miMetodoAbstracto();

            // Utilizando una referencia de método
            EjemploLambda ej = new EjemploLambda();
            MiInterfazFuncionalEjemplo referenciaMetodo = ej::miMetodoImplementado;
            referenciaMetodo.miMetodoAbstracto();
        }

        // Método que implementa el método abstracto de la interfaz
        public void miMetodoImplementado() {
            System.out.println("Implementación mediante referencia de método.");
        }
    }

