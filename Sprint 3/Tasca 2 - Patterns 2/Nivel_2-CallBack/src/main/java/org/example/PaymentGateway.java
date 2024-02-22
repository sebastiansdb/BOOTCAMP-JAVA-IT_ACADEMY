package org.example;

public class PaymentGateway
{
    private PaymentMethod EventListener;
    private double price;

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
