package org.example;

public class Main
{
    /*
                                    CALLBACK Pattern
        1. Define the methods in an interface that we want to invoke after callback.
        2. Define a class that will implement the callback methods of the interface.
        3. Define a reference in other class to register the callback interface.
        4. Use that reference to invoke the callback method.
     */

    public static void main(String[] args) {

        /*
                                                    TO DO
                                           ver variable price en ShoeSHop
         */

        // Creo la tienda (ésta es la que tiene que invocar a la pasarela de pago, segun el enunciado propuesto
        // del ejercicio)
        ShoesShop store = new ShoesShop("Fancy");
        // PaymentMethod es la CallBackInterface
        PaymentMethod paymentMethod1 = new PaymentPayPal();
        // PaymentGateway es la clase que llama al evento ( CALLBACK )
        PaymentGateway sale1 = new PaymentGateway(paymentMethod1);

        // Agrego metodo de pago PayPal a la tienda
        store.addPayment(sale1);
        store.makeACharge(0).sellingProcess();
        // Agrego metodo de pago BankTransfer a la tienda
        store.addPayment(new PaymentGateway(new PaymentBankTransfer()));
        store.makeACharge(1).sellingProcess();
        // Agrego metodo de pago CreditCard a la tienda
        store.addPayment(new PaymentGateway(new PaymentCreditCard()));
        store.makeACharge(2).sellingProcess();
    }
}