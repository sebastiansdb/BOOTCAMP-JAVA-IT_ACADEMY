package org.example;

public class PaymentGateway
{
    PaymentMethod EventListener;
    double price;

    public PaymentGateway(PaymentMethod EventListener)
    {
        this.EventListener = EventListener;
    }

    public void sellingProcess()
    {
        if (EventListener != null)
        {
            EventListener.makePayment();
        }
    }
}
