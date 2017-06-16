package com.example.imtpmd1617.studievoortgang.Models;


public class Student{

    private int studentnummer;
    private String voornaam, achternaam, token;

    public Student(int studentnummer, String voornaam, String achternaam, String token) {
        this.studentnummer = studentnummer;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStudentnummer() {
        return studentnummer;
    }

    public void setStudentnummer(int studentnummer) {
        this.studentnummer = studentnummer;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }
}
