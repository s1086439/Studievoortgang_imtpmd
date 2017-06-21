package com.example.imtpmd1617.studievoortgang.Models;

import java.io.Serializable;

public class Module implements Serializable {

    public int ect, periode, id;
    public double cijfer, fase;
    public String module_naam, module_afkorting;

    public Module(){

    }

    public Module(int id, String module_naam, String module_afkorting, double cijfer, int periode, int fase, int ect) {
        this.id = id;
        this.module_naam = module_naam;
        this.module_afkorting = module_afkorting;
        this.ect = ect;
        this.cijfer = cijfer;
        this.periode = periode;
        this.fase = fase;
    }

    public int getEct() {
        return ect;
    }

    public void setEct(int ect) {
        this.ect = ect;
    }

    public int getPeriode() {
        return periode;
    }

    public void setPeriode(int periode) {
        this.periode = periode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCijfer() {
        return cijfer;
    }

    public void setCijfer(double cijfer) {
        this.cijfer = cijfer;
    }

    public double getFase() {
        return fase;
    }

    public void setFase(double fase) {
        this.fase = fase;
    }

    public String getModule_naam() {
        return module_naam;
    }

    public void setModule_naam(String module_naam) {
        this.module_naam = module_naam;
    }

    public String getModule_afkorting() {
        return module_afkorting;
    }

    public void setModule_afkorting(String module_afkorting) {
        this.module_afkorting = module_afkorting;
    }
}