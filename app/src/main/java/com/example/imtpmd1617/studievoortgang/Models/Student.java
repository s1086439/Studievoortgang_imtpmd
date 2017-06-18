package com.example.imtpmd1617.studievoortgang.Models;


public class Student{

    public int studentId;
    public String voornaam, achternaam, token, studierichting;

    public Student(int studentId, String voornaam, String achternaam, String token, String studierichting) {
        this.studentId = studentId;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.token = token;
        this.studierichting = studierichting;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentnummer) {
        this.studentId = studentId;
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
