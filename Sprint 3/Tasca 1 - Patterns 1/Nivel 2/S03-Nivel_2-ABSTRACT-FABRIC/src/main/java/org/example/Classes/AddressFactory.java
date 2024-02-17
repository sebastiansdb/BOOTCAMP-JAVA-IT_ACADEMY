package org.example.Classes;

import org.example.Interfaces.ContactListFactory;
import java.util.Scanner;

public class AddressFactory implements ContactListFactory
{
    Scanner sc = new Scanner(System.in);
    @Override
    public Address createAddress()
    {
        String street;
        int number;
        String city;
        String country;
        int zip;
        System.out.println("Ingresa la calle: ");
        street = sc.nextLine();
        System.out.println("Ingresa el numero de la calle: ");
        number = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingresa la ciudad: ");
        city = sc.nextLine();
        System.out.println("Ingresa el pais: ");
        country = sc.nextLine();
        System.out.println("Ingresa el codigo postal: ");
        zip = sc.nextInt();
        sc.nextLine();
        return new Address(street, number, city, country, zip);
    }
    @Override
    public Phone createPhone()
    {
        return null;
    }

    @Override
    public Contact createContact(String name, Address address, Phone phone)
    {
        return null;
    }

}
