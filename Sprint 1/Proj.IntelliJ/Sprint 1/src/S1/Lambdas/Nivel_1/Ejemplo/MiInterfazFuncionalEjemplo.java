package S1.Lambdas.Nivel_1.Ejemplo;
@FunctionalInterface
public interface MiInterfazFuncionalEjemplo {

        void miMetodoAbstracto();

        // Otros métodos (default o estáticos) no afectan la "functionalidad" de la interfaz funcional
        default void otroMetodo() {
            System.out.println("Este es otro método en la interfaz funcional.");
        }
    }

