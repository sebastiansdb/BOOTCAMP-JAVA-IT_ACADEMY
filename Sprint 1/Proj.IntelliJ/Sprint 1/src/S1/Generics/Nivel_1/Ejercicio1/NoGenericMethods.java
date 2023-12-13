package S1.Generics.Nivel_1.Ejercicio1;

public class NoGenericMethods <T> {

    private T obA;
    private T obB;
    private T obC;

    public NoGenericMethods (T obA, T obB, T obC)
    {
        this.obA = obA;
        this.obB = obB;
        this.obC = obC;
    }

    public T getObA() {
        return obA;
    }

    public T getObB() {
        return obB;
    }

    public T getObC() {
        return obC;
    }

    public void setObA(T obA) {
        this.obA = obA;
    }

    public void setObB(T obB) {
        this.obB = obB;
    }

    public void setObC(T obC) {
        this.obC = obC;
    }
}
