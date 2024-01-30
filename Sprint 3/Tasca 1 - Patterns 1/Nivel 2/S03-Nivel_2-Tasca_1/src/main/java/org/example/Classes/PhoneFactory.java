package org.example.Classes;

import org.example.Interfaces.ContactListFactory;
import java.util.Scanner;

public class PhoneFactory implements ContactListFactory
{
    Scanner sc = new Scanner(System.in);
    public Phone createPhone()
    {
        int number;
        int areaCode;
        System.out.println("Ingresa el numero de telefono: ");
        number = sc.nextInt();
        System.out.println("Ingresa el codigo del pais: ");
        areaCode = sc.nextInt();
        return new Phone(number, areaCode);
    }

    public Address createAddress()
    {
        return null;
    }
    public Contact createContact(String name, Address address, Phone phone)
    {
        return null;
    }
}
