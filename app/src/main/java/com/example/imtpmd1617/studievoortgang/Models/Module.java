package com.example.imtpmd1617.studievoortgang.Models;

import java.io.Serializable;

public class Module implements Serializable {

    public String naam, studierichting, ect;

    public Module(String naam, String studierichting, String ect) {
        this.naam = naam;
        this.studierichting = studierichting;
        this.ect = ect;
    }
}
