package com.example.akarpenko.organisations;

import java.io.Serializable;

/**
 * Created by a.karpenko on 03.11.2015.
 */
public class Org  implements Serializable {

    String name;
    String phone;
    String address;
    String mail;
    int id;
    public String getMail() {
        return mail;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + phone + "  " + address + ' ' + mail;
    }
}
