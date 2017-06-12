package com.example.imtpmd1617.studievoortgang.Models;

import java.io.Serializable;

public class Module implements Serializable {

    public String naam;
    public String ect;

    public Module(String naam, String ect){
        this.naam = naam;
        this.ect = ect;
    }

    public Module(){
    }
}
