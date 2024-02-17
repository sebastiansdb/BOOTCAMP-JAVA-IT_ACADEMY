package org.example;
/*

Dissenya una classe que mostri en pantalla el preu de diversos articles. Ja que haurà de mostrar-los també en diversos
tipus de moneda.

Important

Assegura't d'afegir-li com a Injecció de Dependència una classe Convertidor de Moneda que efectuï la correcció del
preu en funció del canvi de divisa.

 */
public class Main {
    public static void main(String[] args) {
        // Diseñé una clase producto y es ella misma la que muestra el precio del artículo
        Product p1 = new Product("jarron",10);
        MoneyConverter mCrupia = new MoneyConverter(30, 'r');
        MoneyConverter mCeuro = new MoneyConverter(0.9, '€');
        MoneyConverter mCyen = new MoneyConverter(2, 'Y');
        // Dependency injection: mCeuro, mCyen, mCrupia
        p1.showProductPrice(mCeuro);
        p1.showProductPrice(mCyen);
        p1.showProductPrice(mCrupia);
        // Cambia precio de la rupia
        mCrupia.setCurrencyRate(29.2);
        System.out.println("Actualizacion precio en rupias");
        p1.showProductPrice(mCrupia);
;    }
}