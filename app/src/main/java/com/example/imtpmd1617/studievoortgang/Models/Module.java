package com.example.imtpmd1617.studievoortgang.Models;

import java.io.Serializable;

public class Module implements Serializable {

    public int ect, periode, id;
    public double cijfer;
    public String module_naam, fase, module_afkorting;

    public Module(int id, String module_naam, String module_afkorting, int ect, double cijfer, int periode, String fase) {
        this.id = id;
        this.module_naam = module_naam;
        this.module_afkorting = module_afkorting;
        this.ect = ect;
        this.cijfer = cijfer;
        this.periode = periode;
        this.fase = fase;
    }
}