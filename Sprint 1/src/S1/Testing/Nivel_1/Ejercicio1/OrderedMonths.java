package S1.Testing.Nivel_1.Ejercicio1;
import java.util.Map;
import java.util.TreeMap;

public class OrderedMonths {
    TreeMap<Integer,String> sortedMonths = new TreeMap<>();

    // No pongo constructor porque no me hace falta para este ejemplo.                      ¿Es corecto esto?

    public String getMonth(int key)
    {
        return sortedMonths.get(key);
    }
    public void insertMonth(String month, int monthNumber){
        this.sortedMonths.put(monthNumber,month);
    }

    public void printMonth()
    {
        for (Map.Entry<Integer,String> entry : sortedMonths.entrySet()){
            System.out.println(entry.getValue());
        }
    }

}
