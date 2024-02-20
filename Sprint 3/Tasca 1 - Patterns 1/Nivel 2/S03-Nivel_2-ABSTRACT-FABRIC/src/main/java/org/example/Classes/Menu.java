package org.example.Classes;

import org.example.Interfaces.ContactListFactory;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu
{
    public static void runMenu (ArrayList<Contact> contacts)
    {

        int option;
        String contactName;
        ContactListFactory addressFactory = new AddressFactory();
        ContactListFactory phoneFactory = new PhoneFactory();
        ContactListFactory contactFactory = new ConctactFactory();
        do {
            // Hago un bucle simple
//            System.out.println("Ingresa el nombre del contacto");
//            contactName = sc.nextLine();
            contactName = GetUserData.getString("Ingresa el nombre del contacto");
            contacts.add(contactFactory.createContact(contactName,
                    addressFactory.createAddress(), phoneFactory.createPhone()));
            System.out.println("Ingresa '0' para 'Terminar' o '1' para 'Ingresar otro contacto'");
            option = GetUserData.getInteger("Ingresa '0' para 'Terminar' o '1' para 'Ingresar otro contacto'");
        }
        while(option != 0);
    }
}
