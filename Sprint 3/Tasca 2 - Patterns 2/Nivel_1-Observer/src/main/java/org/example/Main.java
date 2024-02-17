package org.example;

import org.example.Observer.BrokerHouse1;
import org.example.Subject_Observable.StockBroker;

/*
    TO DO

    Classes:
        - Main
        - Subject   (StockBroker)   --
        - Observer ( Abstract)      --
        - BrokerHouse1              --
 */
public class Main {
    public static void main(String[] args) {

        // Esta variable podria no referenciarse y colocarla directamente dentro del constructor "StockBroker()"
        StockBroker stockBroker = new StockBroker();
        new BrokerHouse1(stockBroker);
        stockBroker.setState("'El Bitcoin tendrá una semana de alza'");
        stockBroker.setState("'Ballenas al asecho, estan vendiendo mucho!! Caerá el Bitcoin un 20%'");


    }
}