package org.example;

public class MoneyConverter {
    private double currencyRate;
    private char currencySymbol;
//    private static final double dollarYenRate;
//    private static final double dollarRupiaRate;

    public MoneyConverter(double currencyRate, char currencySymbol){
        this.currencyRate = currencyRate;
        this.currencySymbol = currencySymbol;
    }
    public double getCurrencyRate() {
        return currencyRate;
    }
    public char getCurrencySymbol() {
        return currencySymbol;
    }
    public void setCurrencyRate(double currencyRate) {
         this.currencyRate = currencyRate;
    }

    //    static {
//        dollarEuroRate = 1.09;
//        dollarYenRate = 0.5;
//        dollarRupiaRate = 0.3;
//    }

}
