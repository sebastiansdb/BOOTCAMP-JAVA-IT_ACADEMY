package S1.Collections.Nivel_3;
import java.util.Comparator;

public class PersonIdComparator implements Comparator<Persona>
{
    public int compare(Persona a, Persona b)
    {
        return a.getId().compareTo(b.getId());
    }
}
