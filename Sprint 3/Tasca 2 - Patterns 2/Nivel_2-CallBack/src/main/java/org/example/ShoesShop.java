package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoesShop
{
    private String shopName;
    List<PaymentGateway> paymentList;

    public ShoesShop (String shopName)
    {
        this.shopName = shopName;
        this.paymentList = new ArrayList<>();
    }

    public void addPayment(PaymentGateway paymentGateway)
    {
        paymentList.add(paymentGateway);
    }

    public PaymentGateway makeACharge(int saleId)
    {
        return paymentList.get(saleId);
    }
    
}
