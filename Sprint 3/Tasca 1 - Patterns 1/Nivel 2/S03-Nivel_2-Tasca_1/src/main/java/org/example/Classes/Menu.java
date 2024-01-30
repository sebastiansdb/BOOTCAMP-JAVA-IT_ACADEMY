package org.example.Classes;

import org.example.Interfaces.ContactListFactory;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu
{
    public static void runMenu (ArrayList<Contact> contacts)
    {
        Scanner sc = new Scanner (System.in);
        int option = 1;
        String contactName;
        ContactListFactory addressFactory = new AddressFactory();
        ContactListFactory phoneFactory = new PhoneFactory();
        ContactListFactory contactFactory = new ConctactFactory();
        do {
            // Hago un bucle simple
            System.out.println("Ingresa el nombre del contacto");
            contactName = sc.nextLine();
            contacts.add(contactFactory.createContact(contactName,
                    addressFactory.createAddress(), phoneFactory.createPhone()));
            System.out.println("Ingresa '0' para 'Terminar' o '1' para 'Ingresar otro contacto'");
            option = sc.nextInt();
            sc.nextLine();
        }
        while(option != 0);
    }
}
