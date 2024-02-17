package org.example.Subject_Observable;

import org.example.Observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class StockBroker    // SUBJECT
{
    // Esta seria la clase SUBJECT,
    // segun el modelo de "OBSERVER PATTERN".
    // Es quien avisa de los cambios de estados a los observadores. En este caso, los estados
    // las fluctuacionesen la bolsa.

    private final List<Observer> observers = new ArrayList<>();
    private String stocksState;

    public void setState(String stocksState)
    {
        this.stocksState = stocksState;
        notifyObservers();
    }

    public String getState()
        {
            return stocksState;
        }
    public void     addObserver(Observer observer)
    {
        observers.add(observer);
    }

    // Este método no se usa en la aplicacion que hice, pero podria necesitarse.
    public void removeObserver (Observer observer)
    {
        observers.remove(observer);
    }
    public void notifyObservers()
    {
      observers.forEach(o -> o.update());
    }
}
