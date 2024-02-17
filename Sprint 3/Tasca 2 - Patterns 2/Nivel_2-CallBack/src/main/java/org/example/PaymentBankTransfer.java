package org.example;

public class PaymentBankTransfer implements PaymentMethod {

    @Override
    public void makePayment() {
        System.out.println("Payment done using Bank Trasnsfer");
    }
}
