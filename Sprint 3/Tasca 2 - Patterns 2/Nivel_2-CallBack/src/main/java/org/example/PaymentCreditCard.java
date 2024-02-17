package org.example;

public class PaymentCreditCard implements PaymentMethod
{
    @Override
    public void makePayment()
    {
        System.out.println("Payment done using CreditCard");
    }
}
