package com.example.amado.mycontacts;

import java.util.ArrayList;

/**
 * Created by Amado on 21/01/2015.
 */
public class ContactList extends ArrayList<Contact>{
    private static ContactList sIntance = null;

    private ContactList(){}

    public static ContactList getIntance(){
        if( sIntance == null){
            sIntance = new ContactList();
        }
        return sIntance;

    }
}
