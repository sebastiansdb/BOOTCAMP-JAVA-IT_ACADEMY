package org.example.Observer;

import org.example.Subject_Observable.StockBroker;

public abstract class Observer
{
    protected StockBroker stockBroker;
    public abstract void update();
}
