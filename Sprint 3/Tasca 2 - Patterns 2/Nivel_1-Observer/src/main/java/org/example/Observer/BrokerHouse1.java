package org.example.Observer;

import org.example.Subject_Observable.StockBroker;

public class BrokerHouse1 extends Observer
{
    public BrokerHouse1 (StockBroker stockBroker)
    {
        // Asigno valor al atributo de la clase padre Observer.
        this.stockBroker = stockBroker;
        // Agrego un Observador, en este caso, una instancia de la clase BrokerHouse1, a la lista de observadores de
        // la clase StockBroker. El (this) significa que es la propia instancia de la clase BrokerHouse1 que se le
        // esta pasando como parámetro al método addObserver de la clase StockBroker.
        this.stockBroker.addObserver(this);
    }

    public void update()
    {
        System.out.println("El estado de la bolsa ha cambiado: " + stockBroker.getState());
    }
}
