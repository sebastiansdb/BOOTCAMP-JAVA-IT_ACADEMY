package org.example.Invoker;

import org.example.Commands.IOperation;

import java.util.ArrayList;
import java.util.List;

public class Invoker
{
    private final List<IOperation> maneuverList = new ArrayList<>();

    public void takeOrder (IOperation maneuver)
    {
        maneuverList.add(maneuver);
    }

    public void executeManeuvers()
    {
        /*
        La linea de abajo es lo mismo que hacer:
        for (IOperation m : maneuverList)
        {
            m.execute();
        }
        o
        maneuverList.forEach(m -> m.execute());
         */
        maneuverList.forEach(IOperation::execute);
        maneuverList.clear();
    }

}
