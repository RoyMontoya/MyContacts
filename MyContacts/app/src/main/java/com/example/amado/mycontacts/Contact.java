package com.example.amado.mycontacts;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Amado on 17/01/2015.
 */
public class Contact implements Serializable{

    private String mName;
    public ArrayList<String> emails;
    public ArrayList<String> phoneNumbers;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
