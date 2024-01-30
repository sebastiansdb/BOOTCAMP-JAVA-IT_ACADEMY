package org.example.Interfaces;

import org.example.Classes.Address;
import org.example.Classes.Contact;
import org.example.Classes.Phone;

public interface ContactListFactory
{
    Address createAddress();
    Phone createPhone();
    Contact createContact(String name, Address address, Phone phone);
}
