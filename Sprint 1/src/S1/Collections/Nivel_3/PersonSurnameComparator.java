package S1.Collections.Nivel_3;

import java.util.Comparator;

public class PersonSurnameComparator implements Comparator<Persona>
{
    public int compare(Persona a, Persona b)
    {
        return a.getSurname().compareToIgnoreCase(b.getSurname());
    }
}
