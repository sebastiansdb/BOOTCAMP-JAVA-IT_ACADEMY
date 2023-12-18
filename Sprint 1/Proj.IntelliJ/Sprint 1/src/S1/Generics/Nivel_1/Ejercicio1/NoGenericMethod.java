package S1.Generics.Nivel_1.Ejercicio1;

public class NoGenericMethod<T> {

    private int obA;
    private int obB;
    private int obC;

    public NoGenericMethod(int obA, int obB, int obC)
    {
        this.obA = obA;
        this.obB = obB;
        this.obC = obC;
    }

    public int getObA() {
        return obA;
    }

    public int getObB() {
        return obB;
    }

    public int getObC() {
        return obC;
    }

    public void setObA(int obA) {
        this.obA = obA;
    }

    public void setObB(int obB) {
        this.obB = obB;
    }

    public void setObC(int obC) {
        this.obC = obC;
    }
}
