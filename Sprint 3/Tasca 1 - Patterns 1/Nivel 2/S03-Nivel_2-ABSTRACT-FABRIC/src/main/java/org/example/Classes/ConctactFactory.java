package org.example.Classes;

import org.example.Interfaces.ContactListFactory;

public class ConctactFactory implements ContactListFactory
{
        @Override
        public Contact createContact(String name, Address address, Phone phone)
        {
                return new Contact(name, address, phone);
        }
        @Override
        public Address createAddress()
        {
                return null;
        }
        @Override
        public Phone createPhone()
        {
                return null;
        }

}
